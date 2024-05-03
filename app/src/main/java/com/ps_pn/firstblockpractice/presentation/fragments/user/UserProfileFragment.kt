package com.ps_pn.firstblockpractice.presentation.fragments.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.ps_pn.firstblockpractice.databinding.FragmentUserProfileBinding
import com.ps_pn.firstblockpractice.di.AppComponent
import com.ps_pn.firstblockpractice.presentation.App
import com.ps_pn.firstblockpractice.presentation.ViewModelFactory
import com.ps_pn.firstblockpractice.presentation.adapters.friend.FriendsAdapter
import com.ps_pn.firstblockpractice.presentation.utills.BindingException
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserProfileFragment : Fragment() {
    private var _binding: FragmentUserProfileBinding? = null
    private val binding: FragmentUserProfileBinding
        get() = _binding ?: throw BindingException("FragmentUserProfileBinding is null")

    private val friendAdapter: FriendsAdapter = FriendsAdapter()
    lateinit var viewModel: UserProfileViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val component: AppComponent by lazy {
        (requireActivity().application as App).component
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        component.inject(this)
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[UserProfileViewModel::class.java]
        binding.friendRv.adapter = friendAdapter
        setListeners()
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is UserProfileState.Loading -> {
                            Log.i("TestLOG", "Friends list is : Loading...")
                        }

                        is UserProfileState.Response -> {
                            friendAdapter.submitList(state.friends)
                        }

                        is UserProfileState.Error -> {
                            showErrorMsg()
                        }
                    }
                }
            }
        }
    }

    private fun setListeners() {
        binding.imageButtonEditProfile.setOnClickListener {
            navigateProfileFragment()
        }
    }

    private fun navigateProfileFragment() {
        val direction =
            UserProfileFragmentDirections.actionUserProfileFragmentToEditProfileFragment()
        findNavController().navigate(direction)
    }

    private fun showErrorMsg() {
        Toast.makeText(this@UserProfileFragment.requireContext(), "Some error", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

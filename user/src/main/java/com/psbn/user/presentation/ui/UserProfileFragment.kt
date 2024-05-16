package com.psbn.user.presentation.ui

import android.content.Context
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
import com.psbn.firstblockpractice.core.exception.BindingException
import com.psbn.user.databinding.FragmentUserProfileBinding
import com.psbn.user.di.DaggerUserComponent
import com.psbn.user.di.UserDepsProvider
import com.psbn.user.presentation.adapter.FriendsAdapter
import com.psbn.user.presentation.viewmodel.UserProfileState
import com.psbn.user.presentation.viewmodel.UserProfileViewModel
import com.psbn.user.presentation.viewmodel.UserViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserProfileFragment : Fragment() {
    private var _binding: FragmentUserProfileBinding? = null
    private val binding: FragmentUserProfileBinding
        get() = _binding ?: throw BindingException(
            "FragmentUserProfileBinding is null"
        )

    private val friendAdapter: FriendsAdapter = FriendsAdapter()
    lateinit var viewModel: UserProfileViewModel

    @Inject
    lateinit var viewModelFactory: UserViewModelFactory
    override fun onAttach(context: Context) {
        super.onAttach(context)
        val userDeps = (context.applicationContext as UserDepsProvider).getUserDeps()
        val component = DaggerUserComponent.factory().create(userDeps)
        component.inject(this)
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
        //  component.inject(this)
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

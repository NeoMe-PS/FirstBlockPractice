package com.ps_pn.firstblockpractice.presentation.fragments.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ps_pn.firstblockpractice.data.Mapper
import com.ps_pn.firstblockpractice.data.StubData
import com.ps_pn.firstblockpractice.databinding.FragmentUserProfileBinding
import com.ps_pn.firstblockpractice.presentation.adapters.friend.FriendsAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class UserProfileFragment : Fragment() {
    private var _binding: FragmentUserProfileBinding? = null
    private val binding: FragmentUserProfileBinding
        get() = _binding ?: throw RuntimeException("FragmentUserProfileBinding is null")

    private val friendAdapter: FriendsAdapter = FriendsAdapter()
    private val disposableBag = CompositeDisposable()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.friendRv.adapter = friendAdapter
        fillAdapter()
        setListeners()
    }

    private fun setListeners() {
        binding.imageButtonEditProfile.setOnClickListener {
            openEditProfileFragment()
        }
    }

    private fun openEditProfileFragment() {
        val direction =
            UserProfileFragmentDirections.actionUserProfileFragmentToEditProfileFragment()
        findNavController().navigate(direction)
    }

    private fun fillAdapter() {
        val disposable = StubData.friends.subscribeOn(Schedulers.io())
            .map { dtoResponse -> dtoResponse.map { Mapper.mapDtoFriendToPresentation(it) } }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                friendAdapter.submitList(response)
            }, {
                friendAdapter.submitList(StubData.getFriendsStubData())
            })
        disposableBag.add(disposable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposableBag.clear()
        _binding = null
    }
}

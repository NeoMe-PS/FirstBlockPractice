package com.ps_pn.firstblockpractice.presentation.fragments.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ps_pn.firstblockpractice.data.StubData
import com.ps_pn.firstblockpractice.databinding.FragmentUserProfileBinding
import com.ps_pn.firstblockpractice.presentation.adapter.friend.FriendsAdapter

class UserProfileFragment : Fragment() {
    private var _binding: FragmentUserProfileBinding? = null
    private val binding: FragmentUserProfileBinding
        get() = _binding ?: throw RuntimeException("FragmentUserProfileBinding is null")

    private val friendAdapter: FriendsAdapter = FriendsAdapter()

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
    }

    private fun fillAdapter() {
        friendAdapter.submitList(StubData.fillStubData())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

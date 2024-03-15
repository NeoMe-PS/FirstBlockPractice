package com.ps_pn.firstblockpractice.presentation.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ps_pn.firstblockpractice.databinding.FragmentEventsSearchBinding

class EventsSearchFragment : Fragment() {
    private var _binding: FragmentEventsSearchBinding? = null
    private val binding: FragmentEventsSearchBinding
        get() = _binding ?: throw RuntimeException("FragmentEventsSearchBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventsSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = EventsSearchFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

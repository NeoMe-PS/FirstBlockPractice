package com.ps_pn.firstblockpractice.presentation.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ps_pn.firstblockpractice.data.StubData
import com.ps_pn.firstblockpractice.databinding.FragmentOrgSearchBinding
import com.ps_pn.firstblockpractice.presentation.adapter.search.SearchResultAdapter

class OrgSearchFragment : Fragment() {
    private var _binding: FragmentOrgSearchBinding? = null
    private val binding: FragmentOrgSearchBinding
        get() = _binding ?: throw RuntimeException("FragmentOrgSearchBinding is null")

    private val searchAdapter: SearchResultAdapter = SearchResultAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrgSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fillAdapter()
        setAdapter()
    }

    private fun setAdapter() {
        binding.searchResultRv.adapter = searchAdapter
    }

    private fun fillAdapter() {
        searchAdapter.submitList(StubData.fillSearchResultsStubData())
    }

    override fun onPause() {
        super.onPause()
        fillAdapter()
    }

    companion object {
        @JvmStatic
        fun newInstance() = OrgSearchFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

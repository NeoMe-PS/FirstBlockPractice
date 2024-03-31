package com.ps_pn.firstblockpractice.presentation.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ps_pn.firstblockpractice.data.StubData
import com.ps_pn.firstblockpractice.databinding.FragmentOrgSearchBinding
import com.ps_pn.firstblockpractice.presentation.adapters.search.SearchResultAdapter

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

        setAdapter()
        StubData.searchedDataByOrg.observe(viewLifecycleOwner) { resultList ->
            if (resultList == null) {
                binding.resultsLayout.visibility = View.GONE
                binding.emptyResultLayout.visibility = View.VISIBLE
            } else {
                searchAdapter.submitList(resultList)
                binding.resultsLayout.visibility = View.VISIBLE
                binding.emptyResultLayout.visibility = View.GONE
            }
        }
    }

    private fun setAdapter() {
        binding.searchResultRv.adapter = searchAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

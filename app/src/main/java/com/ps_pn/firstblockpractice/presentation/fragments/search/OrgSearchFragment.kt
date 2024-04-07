package com.ps_pn.firstblockpractice.presentation.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.ps_pn.firstblockpractice.data.StubData
import com.ps_pn.firstblockpractice.databinding.FragmentOrgSearchBinding
import com.ps_pn.firstblockpractice.presentation.adapters.search.SearchResultAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class OrgSearchFragment : Fragment() {
    private var _binding: FragmentOrgSearchBinding? = null
    private val binding: FragmentOrgSearchBinding
        get() = _binding ?: throw RuntimeException("FragmentOrgSearchBinding is null")

    private val searchAdapter: SearchResultAdapter = SearchResultAdapter()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
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
        observeData()
    }

    private fun observeData() {
        coroutineScope.launch {
            StubData.searchedDataByOrg.collect { resultList ->
                if (resultList == null) {
                    binding.resultsLayout.isVisible = false
                    binding.emptyResultLayout.isVisible = true
                    return@collect
                }
                searchAdapter.submitList(resultList)
                binding.resultsLayout.isVisible = true
                binding.emptyResultLayout.isVisible = false
            }
        }
    }

    private fun setAdapter() {
        binding.searchResultRv.adapter = searchAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        coroutineScope.cancel()
    }
}

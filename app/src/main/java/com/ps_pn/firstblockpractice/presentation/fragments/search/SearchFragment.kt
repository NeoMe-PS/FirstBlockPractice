package com.ps_pn.firstblockpractice.presentation.fragments.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.ps_pn.firstblockpractice.R
import com.ps_pn.firstblockpractice.databinding.FragmentSearchBinding
import com.ps_pn.firstblockpractice.presentation.adapter.search.SearchViewPagerAdapter

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = _binding ?: throw RuntimeException("FragmentSearchBinding is null")
    private val adapter: SearchViewPagerAdapter by lazy { SearchViewPagerAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPager()
        setSearchViewParam()
    }

    private fun setSearchViewParam() {
        val searchManager =
            requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.searchBar.apply {
            setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
            setIconifiedByDefault(false)
        }
    }

    private fun setPager() {
        adapter.addFragment(EventsSearchFragment(), getString(R.string.label_events_pager))
        adapter.addFragment(OrgSearchFragment(), getString(R.string.label_org_pager))
        binding.searchPager.adapter = adapter
        TabLayoutMediator(binding.searchTabLayout, binding.searchPager) { tab, position ->
            tab.text = adapter.getTabTitle(position)
        }.attach()
    }

    companion object {
        fun newInstance() = SearchFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

package com.ps_pn.firstblockpractice.presentation.fragments.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.ps_pn.firstblockpractice.R
import com.ps_pn.firstblockpractice.data.StubData
import com.ps_pn.firstblockpractice.databinding.FragmentSearchBinding
import com.ps_pn.firstblockpractice.presentation.adapters.search.SearchViewPagerAdapter
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

const val SEARCH_BY_EVENT_TAG = 1
const val SEARCH_BY_ORG_TAG = 2
private const val EVENT_TAB_POSITION = 0
private const val ORG_TAB_POSITION = 1
private const val QUERY_KEY_EVENT = "event_key"
private const val QUERY_KEY_ORG = "org_key"
private const val SEARCH_BAR_FOCUS_KEY = "search_bar_focus"
private const val EMPTY_STROKE = ""
private const val SEARCH_TIMEOUT = 500L

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = _binding ?: throw RuntimeException("FragmentSearchBinding is null")
    private val adapter: SearchViewPagerAdapter by lazy { SearchViewPagerAdapter(this) }
    private val disposableBag = CompositeDisposable()
    private var eventQuery: String = EMPTY_STROKE
    private var orgQuery: String = EMPTY_STROKE
    private var searchIsActive: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        if (savedInstanceState != null) {
            setSavedState(savedInstanceState)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSearchViewParam()
        setPager()
        setSearchFlow()
        setSearchIconListener()
    }


    private fun setSavedState(savedInstanceState: Bundle) {
        eventQuery = savedInstanceState.getString(QUERY_KEY_EVENT) ?: EMPTY_STROKE
        orgQuery = savedInstanceState.getString(QUERY_KEY_ORG) ?: EMPTY_STROKE
        searchIsActive = savedInstanceState.getBoolean(SEARCH_BAR_FOCUS_KEY)
        if (searchIsActive) {
            showSearchBar()
        }
    }

    private fun showSearchBar() {
        with(binding) {
            searchToolbar.visibility = View.GONE
            searchBar.visibility = View.VISIBLE
            searchBar.isActivated = true
        }
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

        binding.searchTabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                clearSearchField()

                when (tab?.position) {
                    EVENT_TAB_POSITION -> binding.searchBar.setQuery(eventQuery, false)
                    ORG_TAB_POSITION -> binding.searchBar.setQuery(orgQuery, false)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) = Unit

            override fun onTabReselected(tab: TabLayout.Tab?) = Unit

        })
    }

    private fun setSearchFlow() {
        binding.searchBar.getQueryTextChangeStateFlow()
            .debounce(SEARCH_TIMEOUT)
            .map { query -> query.trim() }
            .onEach { query ->
                if (binding.searchPager.currentItem == ORG_TAB_POSITION) {
                    orgQuery = query
                    submitRequest(query, SEARCH_BY_ORG_TAG)
                } else {
                    eventQuery = query
                    submitRequest(query, SEARCH_BY_EVENT_TAG)
                }
            }
            .launchIn(CoroutineScope(Dispatchers.Default))
    }

    private fun submitRequest(query: String, tag: Int) {
        if (query.isEmpty()) {
            StubData.clearSearchedData(tag)
        } else {
            StubData.getSearchResultsStubData(query, tag)
        }
    }

    private fun setSearchIconListener() {
        binding.imageButtonSearch.setOnClickListener {
            showSearchBar()
        }
        val clearButton =
            binding.searchBar.findViewById<AppCompatImageView>(androidx.appcompat.R.id.search_close_btn)
        clearButton.setOnClickListener {
            clearSearchField()
            if (binding.searchPager.currentItem == ORG_TAB_POSITION) {
                orgQuery = EMPTY_STROKE
                StubData.getSearchResultsStubData(orgQuery, SEARCH_BY_ORG_TAG)
            } else {
                eventQuery = EMPTY_STROKE
                StubData.getSearchResultsStubData(eventQuery, SEARCH_BY_EVENT_TAG)
            }
        }
    }

    private fun clearSearchField() {
        with(binding) {
            searchBar.setQuery(EMPTY_STROKE, false)
            searchBar.clearFocus()
        }
    }

    override fun onPause() {
        super.onPause()
        searchIsActive = binding.searchBar.isActivated
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(QUERY_KEY_EVENT, eventQuery)
        outState.putString(QUERY_KEY_ORG, orgQuery)
        outState.putBoolean(SEARCH_BAR_FOCUS_KEY, searchIsActive)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        disposableBag.clear()
    }
}

fun SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {

    val query = MutableStateFlow("")

    setOnQueryTextListener(object : OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            query.value = newText
            return true
        }
    })
    return query
}

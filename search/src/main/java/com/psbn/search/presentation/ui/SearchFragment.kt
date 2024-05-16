package com.psbn.search.presentation.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.psbn.firstblockpractice.core.exception.BindingException
import com.psbn.search.R
import com.psbn.search.databinding.FragmentSearchBinding
import com.psbn.search.di.DaggerSearchComponent
import com.psbn.search.di.SearchDepsProvider
import com.psbn.search.presentation.adapter.SearchViewPagerAdapter
import com.psbn.search.presentation.viewmodel.SearchUIState
import com.psbn.search.presentation.viewmodel.SearchViewModel
import com.psbn.search.presentation.viewmodel.SearchViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

const val SEARCH_BY_EVENT_TAG = 1
const val SEARCH_BY_ORG_TAG = 2
private const val EVENT_TAB_POSITION = 0
private const val ORG_TAB_POSITION = 1
private const val EMPTY_STROKE = ""
private const val SEARCH_TIMEOUT = 500L

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = _binding ?: throw BindingException(
            "FragmentSearchBinding is null"
        )
    private val adapter: SearchViewPagerAdapter by lazy { SearchViewPagerAdapter(this) }
    private val eventSearchFragment = EventsSearchFragment()
    private val orgSearchFragment = OrgSearchFragment()
    lateinit var viewModel: SearchViewModel

    @Inject
    lateinit var viewModelFactory: SearchViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val componentDeps = (context.applicationContext as SearchDepsProvider).getSearchDeps()
        val component =
            DaggerSearchComponent.factory().create(requireActivity().application, componentDeps)
        component.inject(this)
    }

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
        viewModel = ViewModelProvider(this, viewModelFactory)[SearchViewModel::class.java]
        setSearchViewParam()
        setPager()
        setSearchFlow()
        setSearchIconListener()
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is SearchUIState.Started -> {
                            hideSearchBar()
                        }

                        is SearchUIState.ActivatedSearch -> {
                            showSearchBar()
                        }

                        is SearchUIState.EventSearching -> {
                            showSearchBar()
                            setSearchQuery(state.searchValue)
                        }

                        is SearchUIState.OrgSearching -> {
                            showSearchBar()
                            setSearchQuery(state.searchValue)
                        }
                    }
                }
            }
        }

    }

    private fun showSearchBar() {
        with(binding) {
            searchToolbar.isVisible = false
            searchBar.isVisible = true
            searchBar.isActivated = true
        }
    }

    private fun hideSearchBar() {
        with(binding) {
            searchToolbar.isVisible = true
            searchBar.isVisible = false
            searchBar.isActivated = false
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
        adapter.addFragment(eventSearchFragment, getString(R.string.label_events_pager))
        adapter.addFragment(orgSearchFragment, getString(R.string.label_org_pager))
        binding.searchPager.adapter = adapter
        TabLayoutMediator(binding.searchTabLayout, binding.searchPager) { tab, position ->
            tab.text = adapter.getTabTitle(position)
        }.attach()
        binding.searchTabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    EVENT_TAB_POSITION -> viewModel.switchTab(SEARCH_BY_EVENT_TAG)
                    ORG_TAB_POSITION -> viewModel.switchTab(SEARCH_BY_ORG_TAG)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
            override fun onTabReselected(tab: TabLayout.Tab?) = Unit
        })
    }

    private fun setSearchQuery(query: String?) {
        if (query.isNullOrEmpty()) {
            binding.searchBar.setQuery(EMPTY_STROKE, false)
            return
        }
        binding.searchBar.setQuery(query, false)
    }

    private fun setSearchFlow() {
        binding.searchBar.getQueryTextChangeStateFlow()
            .debounce(SEARCH_TIMEOUT)
            .filter { it.isNotEmpty() }
            .map { query -> query.trim() }
            .onEach { query ->
                if (binding.searchPager.currentItem == ORG_TAB_POSITION) {
                    viewModel.submitQuery(query, SEARCH_BY_ORG_TAG)
                } else {
                    viewModel.submitQuery(query, SEARCH_BY_EVENT_TAG)
                }
            }
            .launchIn(CoroutineScope(Dispatchers.Default))
    }

    private fun setSearchIconListener() {
        binding.imageButtonSearch.setOnClickListener {
            showSearchBar()
            viewModel.setStartedState()
        }
        val clearButton =
            binding.searchBar.findViewById<AppCompatImageView>(androidx.appcompat.R.id.search_close_btn)
        clearButton.setOnClickListener {
            clearSearchField()
        }
    }

    private fun clearSearchField() {
        with(binding) {
            searchBar.setQuery(com.psbn.search.presentation.ui.EMPTY_STROKE, false)
            searchBar.clearFocus()
        }
        if (binding.searchPager.currentItem == ORG_TAB_POSITION) {
            viewModel.clearQuery(SEARCH_BY_ORG_TAG)
        } else {
            viewModel.clearQuery(SEARCH_BY_EVENT_TAG)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

fun SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {
    val query = MutableStateFlow(EMPTY_STROKE)
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

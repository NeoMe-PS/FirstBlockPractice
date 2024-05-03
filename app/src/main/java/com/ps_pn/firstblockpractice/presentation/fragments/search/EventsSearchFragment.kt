package com.ps_pn.firstblockpractice.presentation.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ps_pn.firstblockpractice.databinding.FragmentEventsSearchBinding
import com.ps_pn.firstblockpractice.di.AppComponent
import com.ps_pn.firstblockpractice.presentation.App
import com.ps_pn.firstblockpractice.presentation.ViewModelFactory
import com.ps_pn.firstblockpractice.presentation.adapters.search.SearchResultAdapter
import com.ps_pn.firstblockpractice.presentation.mapper.UiMapper
import com.ps_pn.firstblockpractice.presentation.utills.BindingException
import javax.inject.Inject

class EventsSearchFragment : Fragment() {
    private var _binding: FragmentEventsSearchBinding? = null
    private val binding: FragmentEventsSearchBinding
        get() = _binding ?: throw BindingException("FragmentEventsSearchBinding is null")
    private val searchAdapter: SearchResultAdapter = SearchResultAdapter()
    lateinit var viewModel: SearchViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val component: AppComponent by lazy {
        (requireActivity().application as App).component
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventsSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        component.inject(this)
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            requireParentFragment(),
            viewModelFactory
        )[SearchViewModel::class.java]
        setAdapter()
        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.eventData.observe(viewLifecycleOwner) { data ->
            if (data.isNullOrEmpty()) {
                binding.resultsLayout.isVisible = false
                binding.emptyResultLayout.isVisible = true
                return@observe
            }
            val mappedData = data.map { UiMapper.mapDomainToUiSearch(it) }
            searchAdapter.submitList(mappedData)
            binding.resultsLayout.isVisible = true
            binding.emptyResultLayout.isVisible = false
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

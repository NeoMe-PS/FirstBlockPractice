package com.psbn.search.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.psbn.firstblockpractice.core.exception.BindingException
import com.psbn.search.databinding.FragmentEventsSearchBinding
import com.psbn.search.di.DaggerSearchComponent
import com.psbn.search.di.SearchDepsProvider
import com.psbn.search.presentation.adapter.SearchResultAdapter
import com.psbn.search.presentation.viewmodel.SearchViewModel
import com.psbn.search.presentation.viewmodel.SearchViewModelFactory
import javax.inject.Inject

class EventsSearchFragment : Fragment() {
    private var _binding: FragmentEventsSearchBinding? = null
    private val binding: FragmentEventsSearchBinding
        get() = _binding ?: throw BindingException(
            "FragmentEventsSearchBinding is null"
        )
    private val searchAdapter: SearchResultAdapter = SearchResultAdapter()
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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventsSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
            searchAdapter.submitList(data)
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

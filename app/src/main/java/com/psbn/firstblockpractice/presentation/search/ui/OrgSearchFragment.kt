package com.psbn.firstblockpractice.presentation.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.psbn.firstblockpractice.core.App
import com.psbn.firstblockpractice.core.exception.BindingException
import com.psbn.firstblockpractice.databinding.FragmentOrgSearchBinding
import com.psbn.firstblockpractice.di.AppComponent
import com.psbn.firstblockpractice.presentation.ViewModelFactory
import com.psbn.firstblockpractice.presentation.search.adapter.SearchResultAdapter
import com.psbn.firstblockpractice.presentation.search.viewmodel.SearchViewModel
import javax.inject.Inject

class OrgSearchFragment : Fragment() {
    private var _binding: FragmentOrgSearchBinding? = null
    private val binding: FragmentOrgSearchBinding
        get() = _binding ?: throw BindingException(
            "FragmentOrgSearchBinding is null"
        )

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
        _binding = FragmentOrgSearchBinding.inflate(inflater, container, false)
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
        viewModel.orgData.observe(viewLifecycleOwner) { data ->
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

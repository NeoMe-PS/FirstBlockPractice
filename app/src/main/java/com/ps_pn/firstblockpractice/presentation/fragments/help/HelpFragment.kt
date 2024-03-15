package com.ps_pn.firstblockpractice.presentation.fragments.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ps_pn.firstblockpractice.data.StubData
import com.ps_pn.firstblockpractice.databinding.FragmentHelpBinding
import com.ps_pn.firstblockpractice.presentation.adapters.help.CategoryAdapter
import java.util.concurrent.Executors


class HelpFragment : Fragment() {
    private var _binding: FragmentHelpBinding? = null
    private val binding: FragmentHelpBinding
        get() = _binding ?: throw RuntimeException("FragmentHelpBinding is null")

    private val categoryAdapter: CategoryAdapter = CategoryAdapter()
    private val executorService = Executors.newSingleThreadExecutor()
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            isLoading = savedInstanceState.getBoolean(LOADING_STATE_KEY)
        }
        if (!isLoading) {
            loadData()
            isLoading = true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHelpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.categoryRv.adapter = categoryAdapter
        observeDataLoading()
    }

    private fun observeDataLoading() {
        StubData.categoriesIsLoaded.observe(viewLifecycleOwner) {
            if (!it) {
                showProgressBar()
            } else {
                hideProgressBar()
                isLoading = true
                categoryAdapter.submitList(StubData.categoriesData)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(LOADING_STATE_KEY, isLoading)
    }

    private fun showProgressBar() {
        binding.categoryProgressBar.visibility = View.VISIBLE
        binding.categoryRv.visibility = View.INVISIBLE
    }

    private fun hideProgressBar() {
        binding.categoryProgressBar.visibility = View.INVISIBLE
        binding.categoryRv.visibility = View.VISIBLE
    }

    private fun loadData() {
        executorService.submit {
            Thread.sleep(5000)
            StubData.fillCategoriesStubData()
        }
    }

    companion object {
        private const val LOADING_STATE_KEY = "LOADING_STATE_KEY"
        fun newInstance() = HelpFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        executorService.shutdown()
    }
}

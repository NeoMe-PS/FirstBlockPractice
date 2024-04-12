package com.ps_pn.firstblockpractice.presentation.fragments.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.ps_pn.firstblockpractice.data.StubData
import com.ps_pn.firstblockpractice.databinding.FragmentHelpBinding
import com.ps_pn.firstblockpractice.presentation.adapters.help.CategoryAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable

class HelpFragment : Fragment() {
    private var _binding: FragmentHelpBinding? = null
    private val binding: FragmentHelpBinding
        get() = _binding ?: throw RuntimeException("FragmentHelpBinding is null")

    private val categoryAdapter: CategoryAdapter = CategoryAdapter()
    private var isLoading = false
    private val disposableBag = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            isLoading = savedInstanceState.getBoolean(LOADING_STATE_KEY)
        }
        if (!isLoading) {
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

        observeData()
    }

    private fun observeData() {
        val disposable = StubData.categories
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ categories ->
                categoryAdapter.submitList(categories)
                StubData.categoriesIsLoaded.postValue(true)
                hideProgressBar()
            }, {
                hideProgressBar()
            })
        disposableBag.add(disposable)
        observeDataLoading()
    }

    private fun observeDataLoading() {
        StubData.categoriesIsLoaded.observe(viewLifecycleOwner) { isLoaded ->
            if (isLoaded) {
                isLoading = true
            } else {
                showProgressBar()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        disposableBag.clear()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(LOADING_STATE_KEY, isLoading)
    }

    private fun showProgressBar() {
        binding.categoryProgressBar.isVisible = true
        binding.categoryRv.isVisible = false
    }

    private fun hideProgressBar() {
        binding.categoryProgressBar.isVisible = false
        binding.categoryRv.isVisible = true
    }

    companion object {
        private const val LOADING_STATE_KEY = "LOADING_STATE_KEY"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

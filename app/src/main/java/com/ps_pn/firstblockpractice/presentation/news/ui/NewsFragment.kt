package com.ps_pn.firstblockpractice.presentation.news.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.ps_pn.firstblockpractice.core.App
import com.ps_pn.firstblockpractice.core.exception.BindingException
import com.ps_pn.firstblockpractice.databinding.FragmentNewsBinding
import com.ps_pn.firstblockpractice.di.AppComponent
import com.ps_pn.firstblockpractice.presentation.ViewModelFactory
import com.ps_pn.firstblockpractice.presentation.models.EventUI
import com.ps_pn.firstblockpractice.presentation.navigateutill.navigator
import com.ps_pn.firstblockpractice.presentation.news.adapter.NewsAdapter
import com.ps_pn.firstblockpractice.presentation.news.viewmodel.NewsUIState
import com.ps_pn.firstblockpractice.presentation.news.viewmodel.NewsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsFragment : Fragment() {
    private var _binding: FragmentNewsBinding? = null
    private val binding
        get() = _binding ?: throw BindingException("FragmentNewsBinding is null")

    private val newsAdapter: NewsAdapter = NewsAdapter()
    lateinit var viewModel: NewsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val component: AppComponent by lazy {
        (requireActivity().application as App).component
    }

    private val mainDispatcher = Dispatchers.Main

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        component.inject(this)
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]
        setClickListeners()
        binding.newsRv.adapter = newsAdapter
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is NewsUIState.Loading -> {
                            showProgressBar()
                        }

                        is NewsUIState.Response -> {
                            newsAdapter.submitList(state.events)
                            navigator().setNewsBadges(state.viewedNews)
                            hideProgressBar()
                        }

                        is NewsUIState.Error -> {
                            withContext(mainDispatcher) {
                                showErrorMsg()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun showErrorMsg() {
        Toast.makeText(this@NewsFragment.requireContext(), "Some error", Toast.LENGTH_SHORT)
            .show()
    }

    private fun setClickListeners() {
        setFilterButtonOnClick()
        setAdapterOnClickListener()
    }

    private fun setFilterButtonOnClick() {
        binding.imageButtonFilter.setOnClickListener {
            navigateToFilterFragment()
        }
    }

    private fun setAdapterOnClickListener() {
        newsAdapter.onNewsClickListener = { event ->
            readEvent(event)
            navigateToDetailFragment(event)
        }
    }

    private fun readEvent(event: EventUI) {
        if (!event.isRead) {
            viewModel.readEvent(event.id)
        }
    }

    private fun navigateToDetailFragment(event: EventUI) {
        val direction =
            NewsFragmentDirections.actionNewsFragmentToNewsDetailFragment(
                event
            )
        findNavController().navigate(direction)
    }

    private fun navigateToFilterFragment() {
        val direction =
            NewsFragmentDirections.actionNewsFragmentToFilterFragment()
        findNavController().navigate(direction)
    }

    private fun showProgressBar() {
        binding.newsProgressBar.isVisible = true
        binding.newsRv.isVisible = false
    }

    private fun hideProgressBar() {
        binding.newsProgressBar.isVisible = false
        binding.newsRv.isVisible = true
    }
}

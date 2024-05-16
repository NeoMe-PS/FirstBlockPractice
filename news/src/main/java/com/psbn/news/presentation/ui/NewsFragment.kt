package com.psbn.news.presentation.ui

import android.content.Context
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
import com.psbn.firstblockpractice.core.exception.BindingException
import com.psbn.firstblockpractice.core.uiUtills.Navigator
import com.psbn.news.databinding.FragmentNewsBinding
import com.psbn.news.di.DaggerNewsComponent
import com.psbn.news.di.NewsDepsProvider
import com.psbn.news.presentation.adapter.NewsAdapter
import com.psbn.news.presentation.models.EventUI
import com.psbn.news.presentation.viewmodel.NewsModelFactory
import com.psbn.news.presentation.viewmodel.NewsUIState
import com.psbn.news.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

fun Fragment.navigator(): Navigator = requireActivity() as Navigator
class NewsFragment : Fragment() {
    private var _binding: FragmentNewsBinding? = null
    private val binding
        get() = _binding ?: throw BindingException(
            "FragmentNewsBinding is null"
        )

    private val newsAdapter: NewsAdapter = NewsAdapter()
    private lateinit var viewModel: NewsViewModel

    @Inject
    lateinit var viewModelFactory: NewsModelFactory
    private val mainDispatcher = Dispatchers.Main

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val componentDeps = (context.applicationContext as NewsDepsProvider).getNewsDeps()
        val component =
            DaggerNewsComponent.factory().create(requireActivity().application, componentDeps)
        component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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

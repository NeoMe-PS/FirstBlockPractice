package com.ps_pn.firstblockpractice.presentation.fragments.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ps_pn.firstblockpractice.data.StubData
import com.ps_pn.firstblockpractice.databinding.FragmentNewsBinding
import com.ps_pn.firstblockpractice.presentation.adapters.news.NewsAdapter
import com.ps_pn.firstblockpractice.presentation.utills.PreferenceManager
import com.ps_pn.firstblockpractice.presentation.utills.navigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

private const val KEY_NEWS_COUNTER = "news_counter"

class NewsFragment : Fragment() {
    private var _binding: FragmentNewsBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentNewsBinding is null")

    private val newsAdapter: NewsAdapter = NewsAdapter()
    private val fullDataList = StubData.newsData
    private var newsCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            newsCounter = savedInstanceState.getInt(KEY_NEWS_COUNTER, 0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapterOnClickListener()
        binding.newsRv.adapter = newsAdapter
        newsAdapter.submitList(fullDataList)
        observeData()
        observeBadgeCount()
        updateNewsByFilter()
        setFilterButtonOnClick()
    }

    private fun observeBadgeCount() {
        lifecycleScope.launch {
            StubData.budgeFlow.collect { count ->
                newsCounter = count
                navigator().setNewsBadges(newsCounter)
            }
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            StubData.events
                .flowOn(Dispatchers.IO)
                .collect { events ->
                    newsAdapter.submitList(events)
                    StubData.emitToBadge(events.size)
                    hideProgressBar()
                }
        }
        observeDataLoading()
    }

    private fun observeDataLoading() {
        StubData.eventsIsLoaded.observe(viewLifecycleOwner) { isLoaded ->
            if (isLoaded) {
                hideProgressBar()
            } else {
                showProgressBar()
            }
        }
    }

    private fun setFilterButtonOnClick() {
        binding.imageButtonFilter.setOnClickListener {
            val direction = NewsFragmentDirections.actionNewsFragmentToFilterFragment()
            findNavController().navigate(direction)
        }
    }

    private fun setAdapterOnClickListener() {
        newsAdapter.onNewsClickListener = { newsItem ->
            if (!newsItem.isRead) {
                lifecycleScope.launch {
                    StubData.markIsReadEvent(newsItem.id)
                }
                newsItem.isRead = true
                if (newsCounter > 0) {
                    newsCounter -= 1
                    StubData.emitToBadge(newsCounter)
                }
            }
            val direction =
                NewsFragmentDirections.actionNewsFragmentToNewsDetailFragment(newsItem)
            findNavController().navigate(direction)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_NEWS_COUNTER, newsCounter)
    }

    override fun onResume() {
        super.onResume()
        updateNewsByFilter()
        navigator().setNewsBadges(newsCounter)
    }

    private fun showProgressBar() {
        binding.newsProgressBar.visibility = View.VISIBLE
        binding.newsRv.visibility = View.GONE
    }

    private fun hideProgressBar() {
        binding.newsProgressBar.visibility = View.GONE
        binding.newsRv.visibility = View.VISIBLE
    }

    private fun updateNewsByFilter() {
        val filteredList = StubData.filterEvents(
            fullDataList,
            PreferenceManager.filterList
        )
        newsAdapter.submitList(filteredList)
    }
}

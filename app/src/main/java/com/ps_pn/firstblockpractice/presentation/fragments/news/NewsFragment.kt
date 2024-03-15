package com.ps_pn.firstblockpractice.presentation.fragments.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ps_pn.firstblockpractice.data.StubData
import com.ps_pn.firstblockpractice.databinding.FragmentNewsBinding
import com.ps_pn.firstblockpractice.presentation.adapters.news.NewsAdapter
import com.ps_pn.firstblockpractice.presentation.utills.PreferenceManager
import com.ps_pn.firstblockpractice.presentation.utills.navigator

class NewsFragment : Fragment() {
    private var _binding: FragmentNewsBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentNewsBinding is null")

    private val newsAdapter: NewsAdapter = NewsAdapter()
    private val fullDataList = StubData.fillNewsStubData()

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
        updateNewsByFilter()
        setFilterButtonOnClick()
    }

    private fun setFilterButtonOnClick() {
        binding.imageButtonFilter.setOnClickListener {
            this.navigator().openNewsFilterFragment()
        }
    }

    private fun setAdapterOnClickListener() {
        newsAdapter.onNewsClickListener = { newsItem ->
            this.navigator().openNewsDetailFragment(newsItem)
        }
    }

    override fun onResume() {
        super.onResume()
        updateNewsByFilter()
    }

    private fun updateNewsByFilter() {
        val filteredList = StubData.filterNewsStubData(
            fullDataList,
            PreferenceManager.filterList
        )
        newsAdapter.submitList(filteredList)
    }

    companion object {
        fun newInstance() = NewsFragment()
    }
}

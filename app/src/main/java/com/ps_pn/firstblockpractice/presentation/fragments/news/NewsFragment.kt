package com.ps_pn.firstblockpractice.presentation.fragments.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ps_pn.firstblockpractice.data.StubData
import com.ps_pn.firstblockpractice.databinding.FragmentNewsBinding
import com.ps_pn.firstblockpractice.presentation.adapter.news.NewsAdapter

class NewsFragment : Fragment() {
    private var _binding: FragmentNewsBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentNewsBinding is null")

    private val newsAdapter: NewsAdapter = NewsAdapter()
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

        binding.newsRv.adapter = newsAdapter
        fillAdapter()
    }

    private fun fillAdapter() {
        newsAdapter.submitList(StubData.fillNewsStubData())
    }

    companion object {
        fun newInstance() = NewsFragment()
    }
}

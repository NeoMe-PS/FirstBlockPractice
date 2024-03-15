package com.ps_pn.firstblockpractice.presentation.fragments.news

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ps_pn.firstblockpractice.data.LoadNewsService
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
    private val fullDataList = StubData.newsData

    private lateinit var mService: LoadNewsService
    private var mBound: Boolean = false
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as LoadNewsService.LocalBinder
            mService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            mBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireContext().startService(LoadNewsService.newIntent(this.requireContext()))
        Intent(requireContext(), LoadNewsService::class.java).also { intent ->
            requireContext().bindService(intent, connection, Context.BIND_AUTO_CREATE)
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
        observeDataLoading()
        updateNewsByFilter()
        setFilterButtonOnClick()
    }

    private fun observeDataLoading() {
        StubData.newsIsLoaded.observe(viewLifecycleOwner) {
            if (!it) {
                showProgressBar()
            } else {
                newsAdapter.submitList(StubData.newsData)
                hideProgressBar()
            }
        }
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

    private fun showProgressBar() {
        binding.newsProgressBar.visibility = View.VISIBLE
        binding.newsRv.visibility = View.INVISIBLE
    }

    private fun hideProgressBar() {
        binding.newsProgressBar.visibility = View.INVISIBLE
        binding.newsRv.visibility = View.VISIBLE
    }

    private fun updateNewsByFilter() {
        val filteredList = StubData.filterNewsStubData(
            fullDataList,
            PreferenceManager.filterList
        )
        newsAdapter.submitList(filteredList)
    }

    override fun onDestroy() {
        super.onDestroy()
        requireContext().unbindService(connection)
        mBound = false
    }

    companion object {
        fun newInstance() = NewsFragment()
    }
}

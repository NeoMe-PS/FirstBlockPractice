package com.ps_pn.firstblockpractice.presentation.fragments.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.BundleCompat
import androidx.fragment.app.Fragment
import com.ps_pn.firstblockpractice.databinding.FragmentNewsDetailBinding
import com.ps_pn.firstblockpractice.presentation.models.Event
import com.ps_pn.firstblockpractice.presentation.utills.HasCustomBottomBar
import com.ps_pn.firstblockpractice.presentation.utills.navigator

class NewsDetailFragment : Fragment(), HasCustomBottomBar {

    private var eventItemParam: Event? = null
    private var _binding: FragmentNewsDetailBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentNewsDetailBinding is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            eventItemParam = BundleCompat.getParcelable(it, ARG_NEWS_ITEM, Event::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackButton()
        setUIFromArgs()
    }

    private fun setUIFromArgs() {
        eventItemParam?.let { item ->
            with(binding) {
                toolbarLabelDetailedNewsTv.text = item.label
                newsTitleTv.text = item.label
                newsTextFirstPartTv.text = item.shortDesc
                timerTv.text = item.date
                fondNameTv.text = item.company
                addressTv.text = item.address
                phonesTv.text = item.phone
                photoNews1.setImageResource(item.newsImages[0])
                photoNews2.setImageResource(item.newsImages[1])
                photoNews3.setImageResource(item.newsImages[2])
                newsTextFirstPartTv.text = item.shortDesc
                newsTextSecondPartTv.text = item.fullDesc
            }
        }
    }

    private fun setBackButton() {
        binding.imageButtonBack.setOnClickListener {
            this.navigator().showMainBottomNav()
            this.navigator().back()
        }
    }

    companion object {
        private const val ARG_NEWS_ITEM = "NEWS_ITEM"
        @JvmStatic
        fun newInstance(event: Event) = NewsDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_NEWS_ITEM, event)
            }
        }
    }
}
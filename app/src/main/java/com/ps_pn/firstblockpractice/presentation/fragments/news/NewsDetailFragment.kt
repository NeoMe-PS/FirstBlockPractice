package com.ps_pn.firstblockpractice.presentation.fragments.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ps_pn.firstblockpractice.databinding.FragmentNewsDetailBinding
import com.ps_pn.firstblockpractice.presentation.utills.HasCustomBottomBar

class NewsDetailFragment : Fragment(), HasCustomBottomBar {

    private var _binding: FragmentNewsDetailBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentNewsDetailBinding is null")
    private val args: NewsDetailFragmentArgs by navArgs()

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
        val item = args.event
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

    private fun setBackButton() {
        binding.imageButtonBack.setOnClickListener {
            val direction =
                NewsDetailFragmentDirections.actionNewsDetailFragmentToNewsFragment()
            findNavController().navigate(direction)
        }
    }
}
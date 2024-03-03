package com.ps_pn.firstblockpractice.presentation.adapter.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ps_pn.firstblockpractice.databinding.NewsItemBinding

class NewsAdapter : ListAdapter<News, NewsViewHolder>(NewsDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemBinding =
            NewsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        return NewsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            newsLabelTv.text = item.label
            newsImage.setImageResource(item.image)
            newsShortDescTv.text = item.shortDesc
            newsDateTv.text = item.date
        }
    }
}

package com.ps_pn.firstblockpractice.presentation.adapters.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ps_pn.firstblockpractice.databinding.NewsItemBinding
import com.ps_pn.firstblockpractice.presentation.models.Event

class NewsAdapter : ListAdapter<Event, NewsViewHolder>(NewsDiffUtil()) {

     var onNewsClickListener: ((Event) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemBinding =
            NewsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        val viewHolder = NewsViewHolder(itemBinding)
        viewHolder.binding.root.setOnClickListener {
            onNewsClickListener?.invoke(getItem(viewHolder.adapterPosition))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            newsLabelTv.text = item.label
            newsImage.setImageResource(item.thumbnail)
            newsShortDescTv.text = item.shortDesc
            newsDateTv.text = item.date
        }
    }
}

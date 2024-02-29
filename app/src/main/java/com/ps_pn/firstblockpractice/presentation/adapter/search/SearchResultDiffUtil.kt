package com.ps_pn.firstblockpractice.presentation.adapter.search

import androidx.recyclerview.widget.DiffUtil

class SearchResultDiffUtil : DiffUtil.ItemCallback<SearchResult>() {
    override fun areItemsTheSame(
        oldItem: SearchResult,
        newItem: SearchResult,
    ): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(
        oldItem: SearchResult,
        newItem: SearchResult,
    ): Boolean {
        return oldItem == newItem
    }
}

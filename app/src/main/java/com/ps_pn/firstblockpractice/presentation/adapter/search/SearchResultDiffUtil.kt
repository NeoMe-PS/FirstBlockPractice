package com.ps_pn.firstblockpractice.presentation.adapter.search

import androidx.recyclerview.widget.DiffUtil

class SearchResultDiffUtil : DiffUtil.ItemCallback<SearchResultEntity>() {
    override fun areItemsTheSame(
        oldItem: SearchResultEntity,
        newItem: SearchResultEntity,
    ): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(
        oldItem: SearchResultEntity,
        newItem: SearchResultEntity,
    ): Boolean {
        return oldItem == newItem
    }
}

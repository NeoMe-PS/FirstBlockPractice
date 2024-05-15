package com.psbn.firstblockpractice.presentation.search.adapter

import androidx.recyclerview.widget.DiffUtil

class SearchResultDiffUtil : DiffUtil.ItemCallback<SearchResultEntity>() {
    override fun areItemsTheSame(
        oldItem: SearchResultEntity,
        newItem: SearchResultEntity,
    ): Boolean {
        return oldItem.label == newItem.label
    }

    override fun areContentsTheSame(
        oldItem: SearchResultEntity,
        newItem: SearchResultEntity,
    ): Boolean {
        return oldItem == newItem
    }
}

package com.psbn.search.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.psbn.search.databinding.SearchResultItemBinding

class SearchResultAdapter :
    ListAdapter<SearchResultEntity, SearchResultViewHolder>(SearchResultDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val itemBinding =
            SearchResultItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        return SearchResultViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.titleTv.text = item.label
    }
}

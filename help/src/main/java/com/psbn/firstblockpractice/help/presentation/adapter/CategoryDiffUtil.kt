package com.psbn.firstblockpractice.help.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.psbn.firstblockpractice.help.domain.entity.Category

class CategoryDiffUtil : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(
        oldItem: Category,
        newItem: Category,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Category,
        newItem: Category,
    ): Boolean {
        return oldItem == newItem
    }
}

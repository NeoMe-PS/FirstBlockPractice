package com.ps_pn.firstblockpractice.presentation.adapters.help

import androidx.recyclerview.widget.DiffUtil
import com.ps_pn.firstblockpractice.domain.entity.Category

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

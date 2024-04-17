package com.ps_pn.firstblockpractice.presentation.adapters.help

import androidx.recyclerview.widget.DiffUtil

class CategoryDiffUtil : DiffUtil.ItemCallback<CategoryAdapterEntity>() {
    override fun areItemsTheSame(
        oldItem: CategoryAdapterEntity,
        newItem: CategoryAdapterEntity,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CategoryAdapterEntity,
        newItem: CategoryAdapterEntity,
    ): Boolean {
        return oldItem == newItem
    }
}

package com.ps_pn.firstblockpractice.presentation.adapter.help

import androidx.recyclerview.widget.DiffUtil

class CategoryDiffUtil : DiffUtil.ItemCallback<CategoryHelpEntity>() {
    override fun areItemsTheSame(
        oldItem: CategoryHelpEntity,
        newItem: CategoryHelpEntity,
    ): Boolean {
        return oldItem.category.id == newItem.category.id
    }

    override fun areContentsTheSame(
        oldItem: CategoryHelpEntity,
        newItem: CategoryHelpEntity,
    ): Boolean {
        return oldItem == newItem
    }
}

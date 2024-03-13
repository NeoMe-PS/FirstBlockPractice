package com.ps_pn.firstblockpractice.presentation.adapters.help

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ps_pn.firstblockpractice.databinding.CategoryItemBinding

class CategoryAdapter : ListAdapter<CategoryAdapterEntity, CategoryViewHolder>(CategoryDiffUtil()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CategoryViewHolder {
        val itemBinding =
            CategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        return CategoryViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.categoryLabelTv.text = item.name
        holder.binding.categoryImg.setImageResource(item.image)
    }
}

package com.psbn.firstblockpractice.help.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ps_pn.firstblockpractice.help.R
import com.ps_pn.firstblockpractice.help.databinding.CategoryItemBinding
import com.psbn.firstblockpractice.help.domain.entity.Category

class CategoryAdapter : ListAdapter<Category, CategoryViewHolder>(CategoryDiffUtil()) {
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
        holder.binding.categoryLabelTv.text = item.label
        // holder.binding.categoryImg.setImageResource(item.img.toInt())

        // пока просто заглушка
        holder.binding.categoryImg.setImageResource(R.drawable.icon_adult)
    }
}

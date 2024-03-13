package com.ps_pn.firstblockpractice.presentation.adapters.news

import androidx.recyclerview.widget.DiffUtil
import com.ps_pn.firstblockpractice.presentation.models.Event

class NewsDiffUtil : DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem == newItem
    }
}

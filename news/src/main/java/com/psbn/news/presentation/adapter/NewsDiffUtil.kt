package com.psbn.news.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.psbn.news.presentation.models.EventUI

class NewsDiffUtil : DiffUtil.ItemCallback<EventUI>() {
    override fun areItemsTheSame(oldItem: EventUI, newItem: EventUI): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EventUI, newItem: EventUI): Boolean {
        return oldItem == newItem
    }
}

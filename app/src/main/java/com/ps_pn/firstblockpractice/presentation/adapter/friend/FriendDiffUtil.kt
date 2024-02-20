package com.ps_pn.firstblockpractice.presentation.adapter.friend

import androidx.recyclerview.widget.DiffUtil

class FriendDiffUtil : DiffUtil.ItemCallback<Friend>() {
    override fun areItemsTheSame(
        oldItem: Friend,
        newItem: Friend,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Friend,
        newItem: Friend,
    ): Boolean {
        return oldItem == newItem
    }
}

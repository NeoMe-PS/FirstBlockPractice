package com.psbn.firstblockpractice.presentation.user.adapter

import androidx.recyclerview.widget.DiffUtil
import com.psbn.firstblockpractice.domain.user.entity.Friend

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

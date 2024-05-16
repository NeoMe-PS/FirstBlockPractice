package com.psbn.user.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.psbn.user.domain.entity.Friend

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

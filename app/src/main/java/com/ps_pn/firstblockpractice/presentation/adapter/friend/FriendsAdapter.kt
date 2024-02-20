package com.ps_pn.firstblockpractice.presentation.adapter.friend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ps_pn.firstblockpractice.R
import com.ps_pn.firstblockpractice.databinding.FriendItemBinding

class FriendsAdapter : ListAdapter<Friend, FriendViewHolder>(FriendDiffUtil()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): FriendViewHolder {
        val itemBinding =
            FriendItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        return FriendViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val item = getItem(position)

        //Пока устанваливаю тестово
        holder.binding.friendNameTv.text = item.name
        val imageRes =
            when (item.id) {
            1 -> R.drawable.avatar_1
            2 -> R.drawable.avatar_2
            3 -> R.drawable.avatar_3
            else -> R.drawable.avatar_1
        }
        holder.binding.friendAvatarImg.setBackgroundResource(imageRes)
    }
}

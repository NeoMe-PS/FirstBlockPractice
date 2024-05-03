package com.ps_pn.firstblockpractice.presentation.user.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ps_pn.firstblockpractice.R
import com.ps_pn.firstblockpractice.databinding.FriendItemBinding
import com.ps_pn.firstblockpractice.domain.user.entity.Friend

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
        holder.binding.friendNameTv.text = item.name
        Glide.with(holder.binding.friendAvatarImg)
            .load(item.img)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .placeholder(R.drawable.user_icon)
            .error(R.drawable.user_icon)
            .into(holder.binding.friendAvatarImg)
    }
}

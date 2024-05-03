package com.ps_pn.firstblockpractice.data.mapper

import com.ps_pn.firstblockpractice.data.network.dto.FriendDto
import com.ps_pn.firstblockpractice.domain.entity.Friend
import javax.inject.Inject

class FriendMapper @Inject constructor() {

    fun mapDtoFriendToPresentation(friend: FriendDto) = Friend(
        id = friend.id,
        name = friend.name,
        img = friend.img
    )
}

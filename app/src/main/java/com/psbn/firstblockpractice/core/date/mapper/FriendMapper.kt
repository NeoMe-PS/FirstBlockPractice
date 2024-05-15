package com.psbn.firstblockpractice.core.date.mapper

import com.psbn.firstblockpractice.core.data.network.dto.FriendDto
import com.psbn.firstblockpractice.domain.user.entity.Friend
import javax.inject.Inject

class FriendMapper @Inject constructor() {

    fun mapDtoFriendToPresentation(friend: FriendDto) = Friend(
        id = friend.id,
        name = friend.name,
        img = friend.img
    )
}
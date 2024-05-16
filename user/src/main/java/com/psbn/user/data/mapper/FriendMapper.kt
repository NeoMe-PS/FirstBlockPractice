package com.psbn.user.data.mapper

import com.psbn.firstblockpractice.core.data.network.dto.FriendDto
import javax.inject.Inject

class FriendMapper @Inject constructor() {

    fun mapDtoFriendToPresentation(friend: FriendDto) = com.psbn.user.domain.entity.Friend(
        id = friend.id,
        name = friend.name,
        img = friend.img
    )
}
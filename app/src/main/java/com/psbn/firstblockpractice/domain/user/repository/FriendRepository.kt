package com.psbn.firstblockpractice.domain.user.repository

import com.psbn.firstblockpractice.domain.user.entity.Friend
import kotlinx.coroutines.flow.Flow

interface FriendRepository {
    fun getFriends(): Flow<List<Friend>>
}

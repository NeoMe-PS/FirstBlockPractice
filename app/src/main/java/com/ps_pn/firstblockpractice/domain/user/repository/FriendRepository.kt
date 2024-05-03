package com.ps_pn.firstblockpractice.domain.user.repository

import com.ps_pn.firstblockpractice.domain.user.entity.Friend
import kotlinx.coroutines.flow.Flow

interface FriendRepository {
    fun getFriends(): Flow<List<Friend>>
}

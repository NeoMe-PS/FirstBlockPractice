package com.ps_pn.firstblockpractice.domain.repository

import com.ps_pn.firstblockpractice.domain.entity.Friend
import kotlinx.coroutines.flow.Flow

interface FriendRepository {
    fun getFriends(): Flow<List<Friend>>
}

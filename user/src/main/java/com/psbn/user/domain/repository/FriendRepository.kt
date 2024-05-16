package com.psbn.user.domain.repository

import com.psbn.user.domain.entity.Friend
import kotlinx.coroutines.flow.Flow

interface FriendRepository {
    fun getFriends(): Flow<List<Friend>>
}

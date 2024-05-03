package com.ps_pn.firstblockpractice.domain.user.usecase

import com.ps_pn.firstblockpractice.domain.user.entity.Friend
import com.ps_pn.firstblockpractice.domain.user.repository.FriendRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadFriendsUseCase @Inject constructor(private val repository: FriendRepository) {
    operator fun invoke(): Flow<List<Friend>> {
        return repository.getFriends()
    }
}

package com.psbn.user.domain.usecase

import com.psbn.user.domain.entity.Friend
import com.psbn.user.domain.repository.FriendRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadFriendsUseCase @Inject constructor(private val repository: FriendRepository) {
    operator fun invoke(): Flow<List<Friend>> {
        return repository.getFriends()
    }
}

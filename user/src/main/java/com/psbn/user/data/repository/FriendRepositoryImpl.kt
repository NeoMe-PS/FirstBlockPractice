package com.psbn.user.data.repository

import android.util.Log
import com.psbn.firstblockpractice.core.data.network.ApiService
import com.psbn.firstblockpractice.core.data.network.NoConnectivityException
import com.psbn.user.data.mapper.FriendMapper
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FriendRepositoryImpl @Inject constructor(
    private val mapper: FriendMapper,
    private val apiService: ApiService
) : com.psbn.user.domain.repository.FriendRepository {

    override fun getFriends() = flow {

        try {
            val response = apiService.getFriends()
            if (response.isSuccessful) {
                if (!response.body().isNullOrEmpty()) {
                    val friendsDto = response.body().orEmpty()
                    emit(friendsDto.map { mapper.mapDtoFriendToPresentation(it) })
                    return@flow
                }
            } else {
                Log.i("TestLOG", "responseError " + response.errorBody().toString())
            }
        } catch (networkException: NoConnectivityException) {
            Log.i("TestLOG", networkException.toString())
        }
        emit(getFriendsStubData())
    }

    private fun getFriendsStubData(): List<com.psbn.user.domain.entity.Friend> {
        val friends = mutableListOf<com.psbn.user.domain.entity.Friend>()
        friends.add(
            com.psbn.user.domain.entity.Friend(
                id = 1,
                name = "Дмитрий Валерьевич",
                img = ""
            )
        )
        friends.add(
            com.psbn.user.domain.entity.Friend(
                id = 2,
                name = "Евгений Александров",
                img = ""
            )
        )
        friends.add(com.psbn.user.domain.entity.Friend(id = 3, name = "Виктор Кузнецов", img = ""))
        return friends
    }
}


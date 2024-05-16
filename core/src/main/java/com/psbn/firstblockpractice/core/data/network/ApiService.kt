package com.psbn.firstblockpractice.core.data.network

import com.psbn.firstblockpractice.core.data.network.dto.CategoryDto
import com.psbn.firstblockpractice.core.data.network.dto.EventDto
import com.psbn.firstblockpractice.core.data.network.dto.FriendDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("categories")
    suspend fun getCategories(): Response<List<CategoryDto>>

    @GET("events")
    suspend fun getEvents(): Response<List<EventDto>>

    @GET("friends")
    suspend fun getFriends(): Response<List<FriendDto>>

}

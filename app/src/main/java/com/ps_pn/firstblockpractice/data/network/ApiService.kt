package com.ps_pn.firstblockpractice.data.network

import com.ps_pn.firstblockpractice.data.network.dto.CategoryDto
import com.ps_pn.firstblockpractice.data.network.dto.EventDto
import com.ps_pn.firstblockpractice.data.network.dto.FriendDto
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

package com.ps_pn.firstblockpractice.data.network

import com.ps_pn.firstblockpractice.data.network.dto.CategoryDto
import com.ps_pn.firstblockpractice.data.network.dto.FriendDto
import retrofit2.http.GET

interface ApiService {
    @GET("categories")
    suspend fun getCategories(): List<CategoryDto>

    @GET("friends")
    suspend fun getFriends(): List<FriendDto>

}
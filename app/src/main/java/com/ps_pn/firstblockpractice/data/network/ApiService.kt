package com.ps_pn.firstblockpractice.data.network

import com.ps_pn.firstblockpractice.data.network.dto.CategoryDto
import com.ps_pn.firstblockpractice.data.network.dto.FriendDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiService {
    @GET("categories")
    fun getCategories(): Single<List<CategoryDto>>

    @GET("friends")
    fun getFriends(): Single<List<FriendDto>>

}
package com.psbn.firstblockpractice.core.data.network.dto

import com.google.gson.annotations.SerializedName

data class FriendDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("img")
    val img: String
)

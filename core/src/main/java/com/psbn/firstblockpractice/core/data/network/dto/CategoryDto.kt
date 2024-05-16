package com.psbn.firstblockpractice.core.data.network.dto

import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("id") val id: Int,
    @SerializedName("label") val label: String,
    @SerializedName("img") val img: String
)

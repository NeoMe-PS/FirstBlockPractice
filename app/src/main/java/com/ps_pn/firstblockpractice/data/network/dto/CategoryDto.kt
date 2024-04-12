package com.ps_pn.firstblockpractice.data.network.dto

import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("id") val id: Int,
    @SerializedName("isActive")
    private val isActive: Boolean,
    @SerializedName("label") val label: String
)

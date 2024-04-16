package com.ps_pn.firstblockpractice.data.network.dto

import com.google.gson.annotations.SerializedName
import com.ps_pn.firstblockpractice.presentation.models.Category

data class EventDto(
    @SerializedName("address")
    val address: String,
    @SerializedName("categories") val categories: List<Category>,
    @SerializedName("company")
    val company: String,
    @SerializedName("date") val date: String,
    @SerializedName("dateEnd") val dateEnd: Long,
    @SerializedName("dateStart")
    val dateStart: Long,
    @SerializedName("fullDesc") val fullDesc: String,
    @SerializedName("id") val id: Int,
    @SerializedName("label") val label: String,
    @SerializedName("newsImages")
    val newsImages: List<Int>,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("shortDesc") val shortDesc: String,
    @SerializedName("thumbnail")
    val thumbnail: Int,
)

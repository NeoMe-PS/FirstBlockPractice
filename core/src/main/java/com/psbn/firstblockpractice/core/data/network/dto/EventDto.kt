package com.psbn.firstblockpractice.core.data.network.dto

import com.google.gson.annotations.SerializedName
import com.psbn.firstblockpractice.core.data.jsonstorage.models.CategoryJSON


data class EventDto(
    @SerializedName("address")
    val address: String,
    @SerializedName("categories") val categories: List<CategoryJSON>,
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

package com.ps_pn.firstblockpractice.data.network.dto

import com.google.gson.annotations.SerializedName
import com.ps_pn.firstblockpractice.presentation.models.Category

data class EventDto(
    @SerializedName("address")
    private val address: String,
    @SerializedName("categories")
    private val categories: List<Category>,
    @SerializedName("company")
    private val company: String,
    @SerializedName("date")
    private val date: String,
    @SerializedName("dateEnd")
    private val dateEnd: Long,
    @SerializedName("dateStart")
    private val dateStart: Long,
    @SerializedName("fullDesc")
    private val fullDesc: String,
    @SerializedName("id")
    private val id: Int,
    @SerializedName("label")
    private val label: String,
    @SerializedName("newsImages")
    private val newsImages: List<Int>,
    @SerializedName("phone")
    private val phone: String,
    @SerializedName("shortDesc")
    private val shortDesc: String,
    @SerializedName("thumbnail")
    private val thumbnail: Int,
)

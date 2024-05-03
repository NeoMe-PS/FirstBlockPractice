package com.ps_pn.firstblockpractice.data.jsonstorage

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventJSON(
    val id: Int,
    val categories: List<CategoryJSON>,
    val label: String,
    val shortDesc: String,
    val fullDesc: String,
    val date: Long,
    val dateStart: Long,
    val dateEnd: Long,
    val thumbnail: Int,
    val newsImages: List<Int>,
    val address: String,
    val phone: String,
    val company: String,
) : Parcelable

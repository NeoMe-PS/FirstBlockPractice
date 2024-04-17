package com.ps_pn.firstblockpractice.data

import android.os.Parcelable
import com.ps_pn.firstblockpractice.presentation.models.Category
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventDataModel(
    val id: Int,
    val categories: List<Category>,
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
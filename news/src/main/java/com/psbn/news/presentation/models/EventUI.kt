package com.psbn.news.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class EventUI(
    val id: Int,
    val categories: List<CategoryUI>,
    val label: String,
    val shortDesc: String,
    val fullDesc: String,
    val date: String,
    val dateStart: String,
    val dateEnd: String,
    val diffInDays: String,
    val thumbnail: Int,
    val newsImages: List<Int>,
    val address: String,
    val phone: String,
    val company: String,
    var isRead: Boolean
) : Parcelable

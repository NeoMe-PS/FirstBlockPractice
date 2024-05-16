package com.psbn.news.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryUI(
    val id: Int,
    val label: String,
    val img: String
) : Parcelable

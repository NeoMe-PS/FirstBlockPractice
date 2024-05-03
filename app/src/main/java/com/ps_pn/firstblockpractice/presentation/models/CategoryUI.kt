package com.ps_pn.firstblockpractice.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryUI(
    val id: Int,
    val label: String,
    val img: String
) : Parcelable

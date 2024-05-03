package com.ps_pn.firstblockpractice.data.jsonstorage

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryJSON(
    val id: Int,
    val label: String,
    val img: String
) : Parcelable

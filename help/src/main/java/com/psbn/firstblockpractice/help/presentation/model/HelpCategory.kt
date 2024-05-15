package com.psbn.firstblockpractice.help.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HelpCategory(
    val id: Int,
    val label: String,
    val img: String
) : Parcelable

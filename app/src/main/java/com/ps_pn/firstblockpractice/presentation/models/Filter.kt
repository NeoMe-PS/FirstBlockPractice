package com.ps_pn.firstblockpractice.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class Filter(val id: Int, val label: String, var isActive: Boolean) : Parcelable {
    companion object {
        const val KIDS_ID = 1
        const val ADULTS_ID = 2
        const val ELDERLY_ID = 3
        const val ANIMALS_ID = 4
        const val EVENTS_ID = 5
    }
}

object Kids : Filter(KIDS_ID, "Дети", true)
object Adults : Filter(ADULTS_ID, "Взрослые", true)
object Elderly : Filter(ELDERLY_ID, "Пожилые", true)
object Animals : Filter(ANIMALS_ID, "Животные", true)
object Events : Filter(EVENTS_ID, "Мероприятия", true)


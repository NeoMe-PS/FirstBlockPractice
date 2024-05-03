package com.ps_pn.firstblockpractice.domain.search.entity

import com.ps_pn.firstblockpractice.domain.help.entity.Category

data class Event(
    val id: Int,
    val categories: List<Category>,
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
)

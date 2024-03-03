package com.ps_pn.firstblockpractice.presentation.adapter.news

import com.ps_pn.firstblockpractice.presentation.adapter.Category

data class News(
    val id: Int,
    val category: Category,
    val label: String,
    val shortDesc: String,
    val date: String,
    val image: Int,
)

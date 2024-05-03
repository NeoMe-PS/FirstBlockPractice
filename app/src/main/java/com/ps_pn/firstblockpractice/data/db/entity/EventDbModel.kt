package com.ps_pn.firstblockpractice.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ps_pn.firstblockpractice.data.jsonstorage.CategoryJSON

@Entity(tableName = "events")
data class EventDbModel(
    @PrimaryKey
    val id: Int,
    val categories: List<CategoryJSON>,
    val label: String,
    val shortDesc: String,
    val fullDesc: String,
    val date: String,
    val dateStart: Long,
    val dateEnd: Long,
    val thumbnail: Int,
    val newsImages: List<Int>,
    val address: String,
    val phone: String,
    val company: String,
    var isRead: Boolean = false
)

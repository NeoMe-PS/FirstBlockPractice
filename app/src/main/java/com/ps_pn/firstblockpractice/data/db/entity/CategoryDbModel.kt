package com.ps_pn.firstblockpractice.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryDbModel(
    @PrimaryKey
    val id: Int,
    val label: String,
    val img: String
)
package com.ps_pn.firstblockpractice.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ps_pn.firstblockpractice.presentation.models.Category

class DataConverter {

    @TypeConverter
    fun fromCategoryList(value: List<Category>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Category>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCategoryList(value: String): List<Category> {
        val gson = Gson()
        val type = object : TypeToken<List<Category>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromImagesList(value: List<Int>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Int>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toImagesList(value: String): List<Int> {
        val gson = Gson()
        val type = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(value, type)
    }
}
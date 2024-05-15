package com.psbn.firstblockpractice.core.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.psbn.firstblockpractice.core.data.jsonstorage.models.CategoryJSON

class DataConverter {

    @TypeConverter
    fun fromCategoryList(value: List<CategoryJSON>): String {
        val gson = Gson()
        val type = object : TypeToken<List<CategoryJSON>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCategoryList(value: String): List<CategoryJSON> {
        val gson = Gson()
        val type = object : TypeToken<List<CategoryJSON>>() {}.type
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

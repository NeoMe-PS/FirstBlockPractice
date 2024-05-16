package com.psbn.firstblockpractice.core.data.jsonstorage

import android.app.Application
import com.google.gson.Gson
import com.psbn.firstblockpractice.core.data.jsonstorage.models.CategoryJSON
import com.psbn.firstblockpractice.core.data.jsonstorage.models.EventJSON
import javax.inject.Inject

class JSONParser @Inject constructor(private val application: Application) {

    companion object {
        private const val JSON_NAME_NEWS = "newsJson.json"
        private const val JSON_NAME_CATEGORIES = "categoriesJson.json"
    }

    private val gson = Gson()
    fun getEventsFromJson(): List<EventJSON> {
        val jsonAsString = application.assets
            .open(JSON_NAME_NEWS)
            .bufferedReader().use {
                it.readText()
            }
        val result = gson.fromJson(jsonAsString, Array<EventJSON>::class.java)
        return result.asList()
    }

    fun getCategoriesFromJson(): List<CategoryJSON> {
        val jsonAsString = application.assets
            .open(JSON_NAME_CATEGORIES)
            .bufferedReader().use {
                it.readText()
            }
        return gson.fromJson(jsonAsString, Array<CategoryJSON>::class.java).asList()
    }
}

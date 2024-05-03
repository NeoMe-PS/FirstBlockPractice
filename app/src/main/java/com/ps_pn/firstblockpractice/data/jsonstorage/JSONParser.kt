package com.ps_pn.firstblockpractice.data.jsonstorage

import com.google.gson.Gson
import com.ps_pn.firstblockpractice.core.App

object JSONParser {

    private const val JSON_NAME_NEWS = "newsJson.json"
    private const val JSON_NAME_CATEGORIES = "categoriesJson.json"
    private val gson = Gson()
    fun getEventsFromJson(): List<EventJSON> {
        val jsonAsString = App.instance.assets
            .open(JSON_NAME_NEWS)
            .bufferedReader().use {
                it.readText()
            }
        val result = gson.fromJson(jsonAsString, Array<EventJSON>::class.java)
        return result.asList()
    }

    fun getCategoriesFromJson(): List<CategoryJSON> {
        val jsonAsString = App.instance.assets
            .open(JSON_NAME_CATEGORIES)
            .bufferedReader().use {
                it.readText()
            }
        return gson.fromJson(jsonAsString, Array<CategoryJSON>::class.java).asList()
    }
}

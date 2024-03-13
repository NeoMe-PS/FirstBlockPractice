package com.ps_pn.firstblockpractice.data

import com.google.gson.Gson
import com.ps_pn.firstblockpractice.presentation.App
import com.ps_pn.firstblockpractice.presentation.models.Category

class JSONParser {

    companion object {
        private const val JSON_NAME_NEWS = "newsJson.json"
        private const val JSON_NAME_CATEGORIES = "categoriesJson.json"
        private val gson = Gson()
        fun getNewsFromJson(): List<EventDataModel> {
            val jsonAsString = App.instance.assets
                .open(JSON_NAME_NEWS)
                .bufferedReader().use {
                    it.readText()
                }
            val result = gson.fromJson(jsonAsString, Array<EventDataModel>::class.java)
            return result.asList()
        }

        fun getCategoriesFromJson(): List<Category> {
            val jsonAsString = App.instance.assets
                .open(JSON_NAME_CATEGORIES)
                .bufferedReader().use {
                    it.readText()
                }
            return gson.fromJson(jsonAsString, Array<Category>::class.java).asList()
        }
    }
}

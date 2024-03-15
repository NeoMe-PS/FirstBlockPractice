package com.ps_pn.firstblockpractice.presentation.utills

import android.content.Context
import androidx.core.content.edit
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.ps_pn.firstblockpractice.presentation.App
import com.ps_pn.firstblockpractice.presentation.FILTER_PREFERENCES
import com.ps_pn.firstblockpractice.presentation.models.Filter

object PreferenceManager {

    private val filteredGson = GsonBuilder().registerTypeAdapter(
        Filter::class.java,
        FilterDeserializer()
    ).create()

    private val filterPrefs =
        App.instance.getSharedPreferences(FILTER_PREFERENCES, Context.MODE_PRIVATE)

    val filterList = getListFilterPreference()

    private fun getListFilterPreference(): List<Filter> {
        val settings: MutableList<Filter> = mutableListOf()

        val string = filterPrefs.getString(FILTER_PREFERENCES, "")
        if (string != "") {
            val type = object : TypeToken<List<Filter>>() {}.type
            val json = filteredGson.fromJson<List<Filter>>(string, type)
            settings.addAll(json)
        } else {
            settings.add(Filter.Kids)
            settings.add(Filter.Adults)
            settings.add(Filter.Elderly)
            settings.add(Filter.Animals)
            settings.add(Filter.Events)
        }
        return settings
    }

    fun getFilterPref(typeId: Int): Filter {
        return filterList.find { it.id == typeId }
            ?: throw Exception("Filter id not found in save settings id: $typeId")
    }

    fun saveFilterSettings() {
        filterPrefs.edit {
            val jsonSettings = filteredGson.toJson(filterList)
            putString(FILTER_PREFERENCES, jsonSettings).apply()
        }
    }
}
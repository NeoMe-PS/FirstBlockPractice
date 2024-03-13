package com.ps_pn.firstblockpractice.presentation.utills

import android.content.Context
import androidx.core.content.edit
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.ps_pn.firstblockpractice.presentation.App
import com.ps_pn.firstblockpractice.presentation.FILTER_PREFERENCES
import com.ps_pn.firstblockpractice.presentation.models.Adults
import com.ps_pn.firstblockpractice.presentation.models.Animals
import com.ps_pn.firstblockpractice.presentation.models.Elderly
import com.ps_pn.firstblockpractice.presentation.models.Events
import com.ps_pn.firstblockpractice.presentation.models.Filter
import com.ps_pn.firstblockpractice.presentation.models.Kids

object PreferenceManager {

    private val filteredGson = GsonBuilder().registerTypeAdapter(
        Filter::class.java,
        FilterDeserializer()
    ).create()

    private val filterPrefs =
        App.instance.getSharedPreferences(FILTER_PREFERENCES, Context.MODE_PRIVATE)

    fun getFilterPreference(): List<Filter> {
        val settings: MutableList<Filter> = mutableListOf()

        val string = filterPrefs.getString(FILTER_PREFERENCES, "")
        if (string != "") {
            val type = object : TypeToken<List<Filter>>() {}.type
            val json = filteredGson.fromJson<List<Filter>>(string, type)
            settings.addAll(json)
        } else {
            settings.add(Kids)
            settings.add(Adults)
            settings.add(Elderly)
            settings.add(Animals)
            settings.add(Events)
        }
        return settings
    }

    fun saveFilterSettings(settings: List<Filter>) {
        filterPrefs.edit {
            val jsonSettings = filteredGson.toJson(settings)
            putString(FILTER_PREFERENCES, jsonSettings).apply()
        }
    }
}
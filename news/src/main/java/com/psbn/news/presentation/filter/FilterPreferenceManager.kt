package com.psbn.news.presentation.filter

import android.app.Application
import android.content.Context
import androidx.core.content.edit
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.psbn.firstblockpractice.core.exception.FilterPrefException
import com.psbn.news.presentation.models.Filter
import javax.inject.Inject

private const val FILTER_PREFERENCES = "FILTER_PREFERENCES"
private const val DEFAULT_VALUE = ""

class FilterPreferenceManager @Inject constructor(application: Application) {

    private val filteredGson = GsonBuilder().registerTypeAdapter(
        Filter::class.java,
        FilterDeserializer()
    ).create()

    private val filterPrefs =
        application.getSharedPreferences(FILTER_PREFERENCES, Context.MODE_PRIVATE)

    val filterList = getListFilterPreference()

    private fun getListFilterPreference(): List<Filter> {
        val settings: MutableList<Filter> = mutableListOf()

        val string = filterPrefs.getString(FILTER_PREFERENCES, DEFAULT_VALUE)
        if (string != DEFAULT_VALUE) {
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
            ?: throw FilterPrefException(
                typeId
            )
    }

    fun saveFilterSettings() {
        filterPrefs.edit {
            val jsonSettings = filteredGson.toJson(filterList)
            putString(FILTER_PREFERENCES, jsonSettings).apply()
        }
    }
}
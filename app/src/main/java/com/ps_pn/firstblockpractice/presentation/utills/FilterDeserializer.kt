package com.ps_pn.firstblockpractice.presentation.utills

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.ps_pn.firstblockpractice.presentation.models.Adults
import com.ps_pn.firstblockpractice.presentation.models.Animals
import com.ps_pn.firstblockpractice.presentation.models.Elderly
import com.ps_pn.firstblockpractice.presentation.models.Events
import com.ps_pn.firstblockpractice.presentation.models.Filter
import com.ps_pn.firstblockpractice.presentation.models.Kids
import java.lang.reflect.Type

class FilterDeserializer : JsonDeserializer<Filter> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Filter {
        val type = json!!.asJsonObject["id"].asString
        return when (type) {
            Filter.KIDS_ID.toString() -> context!!.deserialize(json, Kids::class.java)
            Filter.ADULTS_ID.toString() -> context!!.deserialize(json, Adults::class.java)
            Filter.ELDERLY_ID.toString() -> context!!.deserialize(json, Elderly::class.java)
            Filter.ANIMALS_ID.toString() -> context!!.deserialize(json, Animals::class.java)
            Filter.EVENTS_ID.toString() -> context!!.deserialize(json, Events::class.java)
            else -> throw IllegalArgumentException("Unknown type of filter settings :$type")
        }
    }
}
package com.psbn.firstblockpractice.presentation.utills.prefmanager.filter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.psbn.firstblockpractice.core.exception.FilterDeserializerException
import com.psbn.firstblockpractice.presentation.models.Filter
import java.lang.reflect.Type

class FilterDeserializer : JsonDeserializer<Filter> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Filter {
        val type = json.asJsonObject["id"].asString
        return when (type) {
            Filter.KIDS_ID.toString() -> context.deserialize(json, Filter.Kids::class.java)
            Filter.ADULTS_ID.toString() -> context.deserialize(json, Filter.Adults::class.java)
            Filter.ELDERLY_ID.toString() -> context.deserialize(json, Filter.Elderly::class.java)
            Filter.ANIMALS_ID.toString() -> context.deserialize(json, Filter.Animals::class.java)
            Filter.EVENTS_ID.toString() -> context.deserialize(json, Filter.Events::class.java)
            else -> throw FilterDeserializerException(
                type
            )
        }
    }
}
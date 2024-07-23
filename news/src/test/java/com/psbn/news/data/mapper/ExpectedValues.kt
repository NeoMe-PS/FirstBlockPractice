package com.psbn.news.data.mapper

import com.psbn.firstblockpractice.core.data.db.entity.EventDbModel
import com.psbn.firstblockpractice.core.data.jsonstorage.models.CategoryJSON
import com.psbn.firstblockpractice.core.data.jsonstorage.models.EventJSON
import com.psbn.firstblockpractice.core.data.network.dto.EventDto
import com.psbn.news.domain.entity.Category
import com.psbn.news.domain.entity.Event

val expectedPres = Event(
    id = 1,
    categories = listOf(Category(1, "asd", "asd")),
    label = "event.label",
    shortDesc = "event.shortDesc",
    fullDesc = "event.fullDesc",
    date = "Осталось 0 дней (07.17 - 07.17)",
    dateStart = "07.17",
    dateEnd = "07.17",
    thumbnail = 123,
    newsImages = listOf(3, 2),
    address = "event.address",
    phone = "event.phone",
    company = "event.company",
    diffInDays = "0",
    isRead = false
)
val expectedDB = EventDbModel(
    id = 1,
    categories = listOf(CategoryJSON(1, "asd", "asd")),
    label = "event.label",
    shortDesc = "event.shortDesc",
    fullDesc = "event.fullDesc",
    date = "1721239832846",
    dateStart = 1721239832846,
    dateEnd = 1721239832846,
    thumbnail = 123,
    newsImages = listOf(3, 2),
    address = "event.address",
    phone = "event.phone",
    company = "event.company",
    isRead = false
)
val inputJSON = EventJSON(
    id = 1,
    categories = listOf(CategoryJSON(1, "asd", "asd")),
    label = "event.label",
    shortDesc = "event.shortDesc",
    fullDesc = "event.fullDesc",
    date = 1721239832846,
    dateStart = 1721239832846,
    dateEnd = 1721239832846,
    thumbnail = 123,
    newsImages = listOf(3, 2),
    address = "event.address",
    phone = "event.phone",
    company = "event.company"
)
val inputDto = EventDto(
    id = 1,
    categories = listOf(CategoryJSON(1, "asd", "asd")),
    label = "event.label",
    shortDesc = "event.shortDesc",
    fullDesc = "event.fullDesc",
    date = "1721239832846",
    dateStart = 1721239832846,
    dateEnd = 1721239832846,
    thumbnail = 123,
    newsImages = listOf(3, 2),
    address = "event.address",
    phone = "event.phone",
    company = "event.company"
)
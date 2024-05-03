package com.ps_pn.firstblockpractice.presentation.mapper

import com.ps_pn.firstblockpractice.domain.entity.Category
import com.ps_pn.firstblockpractice.domain.entity.Event
import com.ps_pn.firstblockpractice.presentation.adapters.search.SearchResultEntity
import com.ps_pn.firstblockpractice.presentation.models.CategoryUI
import com.ps_pn.firstblockpractice.presentation.models.EventUI

object UiMapper {

    fun mapDomainToUi(event: Event) = EventUI(
        id = event.id,
        categories = event.categories.map { mapDomainToUi(it) },
        label = event.label,
        shortDesc = event.shortDesc,
        fullDesc = event.fullDesc,
        date = event.date,
        dateStart = event.dateStart,
        dateEnd = event.dateEnd,
        thumbnail = event.thumbnail,
        newsImages = event.newsImages,
        address = event.address,
        phone = event.phone,
        company = event.company,
        diffInDays = event.diffInDays,
        isRead = event.isRead
    )

    fun mapDomainToUiSearch(event: Event) = SearchResultEntity(label = event.label)

    fun mapDomainToUi(category: Category) = CategoryUI(
        id = category.id,
        label = category.label,
        img = category.img
    )
}

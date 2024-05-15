package com.psbn.firstblockpractice.domain.news.mapper

import com.psbn.firstblockpractice.domain.news.entity.Event
import com.psbn.firstblockpractice.help.domain.entity.Category
import com.psbn.firstblockpractice.presentation.models.CategoryUI
import com.psbn.firstblockpractice.presentation.models.EventUI

object EventMapper {
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

    private fun mapDomainToUi(category: Category) = CategoryUI(
        id = category.id,
        label = category.label,
        img = category.img
    )
}

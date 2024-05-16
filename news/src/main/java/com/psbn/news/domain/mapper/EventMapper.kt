package com.psbn.news.domain.mapper

import com.psbn.news.domain.entity.Category
import com.psbn.news.domain.entity.Event
import com.psbn.news.presentation.models.CategoryUI
import com.psbn.news.presentation.models.EventUI

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

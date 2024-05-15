package com.psbn.firstblockpractice.domain.search.mapper

import com.psbn.firstblockpractice.domain.news.entity.Event
import com.psbn.firstblockpractice.presentation.search.adapter.SearchResultEntity

object SearchMapper {
    fun mapDomainToUiSearch(event: Event) = SearchResultEntity(label = event.label)
}

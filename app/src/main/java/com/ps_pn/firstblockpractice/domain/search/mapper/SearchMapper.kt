package com.ps_pn.firstblockpractice.domain.search.mapper

import com.ps_pn.firstblockpractice.domain.news.entity.Event
import com.ps_pn.firstblockpractice.presentation.search.adapter.SearchResultEntity

object SearchMapper {
    fun mapDomainToUiSearch(event: Event) = SearchResultEntity(label = event.label)
}

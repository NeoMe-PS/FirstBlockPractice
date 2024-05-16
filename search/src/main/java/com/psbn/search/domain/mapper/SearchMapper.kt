package com.psbn.search.domain.mapper

import com.psbn.search.domain.entity.Event
import com.psbn.search.presentation.adapter.SearchResultEntity

object SearchMapper {
    fun mapDomainToUiSearch(event: Event) = SearchResultEntity(label = event.label)
}

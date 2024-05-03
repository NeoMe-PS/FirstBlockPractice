package com.ps_pn.firstblockpractice.presentation.search.viewmodel

import com.ps_pn.firstblockpractice.domain.news.entity.Event

sealed class SearchUIState {
    object Started : SearchUIState()
    object ActivatedSearch : SearchUIState()

    data class EventSearching(
        var searchValue: String? = null,
        var result: List<Event>? = null
    ) : SearchUIState()

    data class OrgSearching(
        var searchValue: String? = null,
        var result: List<Event>? = null
    ) : SearchUIState()
}

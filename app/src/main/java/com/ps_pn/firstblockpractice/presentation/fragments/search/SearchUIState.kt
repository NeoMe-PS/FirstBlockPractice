package com.ps_pn.firstblockpractice.presentation.fragments.search

import com.ps_pn.firstblockpractice.domain.entity.Event

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

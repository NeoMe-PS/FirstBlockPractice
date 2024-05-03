package com.ps_pn.firstblockpractice.presentation.news.viewmodel

import com.ps_pn.firstblockpractice.presentation.models.EventUI

sealed class NewsUIState {
    object Loading : NewsUIState()
    object Error : NewsUIState()
    data class Response(val events: List<EventUI>, var viewedNews: Int) : NewsUIState()
}

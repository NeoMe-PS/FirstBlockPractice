package com.psbn.news.presentation.viewmodel

import com.psbn.news.presentation.models.EventUI

sealed class NewsUIState {
    object Loading : NewsUIState()
    object Error : NewsUIState()
    data class Response(val events: List<EventUI>, var viewedNews: Int) : NewsUIState()
}

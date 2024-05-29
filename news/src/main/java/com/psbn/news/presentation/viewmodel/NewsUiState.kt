package com.psbn.news.presentation.viewmodel

import com.psbn.news.presentation.models.EventUI

data class NewsUiState(
    val events: List<EventUI> = listOf(),
    var viewedNews: Int = 0,
    var isError: Boolean = false,
    var isLoading: Boolean = true
)

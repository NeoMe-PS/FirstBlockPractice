package com.psbn.news.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.psbn.news.domain.entity.Event
import com.psbn.news.domain.mapper.EventMapper
import com.psbn.news.domain.usecase.LoadEventsUseCase
import com.psbn.news.domain.usecase.ReadEventUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val loadEventsUseCase: LoadEventsUseCase,
    private val readEventUseCase: ReadEventUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(NewsUiState())
    val uiState: StateFlow<NewsUiState> = _uiState.asStateFlow()

    private val _badges = MutableStateFlow(0)
    val badges: StateFlow<Int> = _badges.asStateFlow()

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private fun getReadEventsCount(events: List<Event>) = events.filter { !it.isRead }.size

    init {
        setState()
    }

    private fun setState() {
        scope.launch {
            loadEventsUseCase.invoke()
                .map { list ->
                    _badges.value = getReadEventsCount(list)
                    _uiState.value.copy(
                        events = list.map { EventMapper.mapDomainToUi(it) },
                        viewedNews = getReadEventsCount(list),
                        isLoading = false
                    )
                }
                .collect { state ->
                    _uiState.value = state
                }
        }
    }

    fun readEvent(id: Int) {
        scope.launch {
            readEventUseCase(id)
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}
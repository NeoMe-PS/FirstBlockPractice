package com.ps_pn.firstblockpractice.presentation.fragments.news

import androidx.lifecycle.ViewModel
import com.ps_pn.firstblockpractice.domain.entity.Event
import com.ps_pn.firstblockpractice.domain.usecases.LoadEventsUseCase
import com.ps_pn.firstblockpractice.domain.usecases.ReadEventUseCase
import com.ps_pn.firstblockpractice.presentation.mapper.UiMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val loadEventsUseCase: LoadEventsUseCase,
    private val readEventUseCase: ReadEventUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<NewsUIState>(NewsUIState.Loading)
    val uiState: StateFlow<NewsUIState> = _uiState

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    init {
        setState()
    }

    private fun setState() {
        scope.launch {
            loadEventsUseCase.invoke()
                .map { list ->
                    NewsUIState.Response(
                        list.map { UiMapper.mapDomainToUi(it) },
                        getReadEventsCount(list)
                    ) as NewsUIState

                }
                .onStart { emit(NewsUIState.Loading) }
                .collect { state ->
                    _uiState.value = state
                }
        }
    }

    private fun getReadEventsCount(events: List<Event>) = events.filter { !it.isRead }.size

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

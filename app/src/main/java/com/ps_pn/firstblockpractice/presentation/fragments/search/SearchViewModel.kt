package com.ps_pn.firstblockpractice.presentation.fragments.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ps_pn.firstblockpractice.domain.entity.Event
import com.ps_pn.firstblockpractice.domain.usecases.SearchEventByEventUseCase
import com.ps_pn.firstblockpractice.domain.usecases.SearchEventByOrgUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val EMPTY_STROKE = ""

class SearchViewModel @Inject constructor(
    private val searchEventByOrgUseCase: SearchEventByOrgUseCase,
    private val searchEventByLabelUseCase: SearchEventByEventUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<SearchUIState>(SearchUIState.Started)
    val uiState: StateFlow<SearchUIState> = _uiState

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private val eventTabStateCache = SearchUIState.EventSearching()
    private val orgTabStateCache = SearchUIState.OrgSearching()

    private val _eventData = MutableLiveData<List<Event>>()
    val eventData: LiveData<List<Event>>
        get() = _eventData
    private val _orgData = MutableLiveData<List<Event>>()
    val orgData: LiveData<List<Event>>
        get() = _orgData

    fun submitQuery(query: String, tabTag: Int) {
        scope.launch {
            when (tabTag) {
                SEARCH_BY_EVENT_TAG -> {
                    val response = searchEventByLabelUseCase(query)
                    eventTabStateCache.searchValue = query
                    eventTabStateCache.result = response
                    _eventData.postValue(response)

                    _uiState.value = SearchUIState.EventSearching(
                        searchValue = query,
                        result = response
                    )
                }

                SEARCH_BY_ORG_TAG -> {
                    val response = searchEventByOrgUseCase(query)
                    orgTabStateCache.searchValue = query
                    orgTabStateCache.result = response
                    _orgData.postValue(response)

                    _uiState.value = SearchUIState.OrgSearching(
                        searchValue = query,
                        result = response
                    )
                }
            }
        }
    }

    fun switchTab(tag: Int) {
        when (tag) {
            SEARCH_BY_EVENT_TAG -> _uiState.value = eventTabStateCache
            SEARCH_BY_ORG_TAG -> _uiState.value = orgTabStateCache
        }
    }

    fun setStartedState() {
        _uiState.value = SearchUIState.ActivatedSearch
    }

    fun clearQuery(tag: Int) {
        when (tag) {
            SEARCH_BY_EVENT_TAG -> {
                eventTabStateCache.searchValue = EMPTY_STROKE
                eventTabStateCache.result = emptyList()
                _eventData.postValue(emptyList())

                _uiState.value = SearchUIState.EventSearching(
                    searchValue = EMPTY_STROKE,
                    result = emptyList()
                )
            }

            SEARCH_BY_ORG_TAG -> {
                orgTabStateCache.searchValue = EMPTY_STROKE
                orgTabStateCache.result = emptyList()
                _orgData.postValue(emptyList())

                _uiState.value = SearchUIState.OrgSearching(
                    searchValue = EMPTY_STROKE,
                    result = emptyList()
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}

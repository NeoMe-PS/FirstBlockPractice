package com.ps_pn.firstblockpractice.data

import androidx.lifecycle.MutableLiveData
import com.ps_pn.firstblockpractice.data.network.ApiFactory
import com.ps_pn.firstblockpractice.presentation.adapters.help.CategoryAdapterEntity
import com.ps_pn.firstblockpractice.presentation.adapters.search.SearchResultEntity
import com.ps_pn.firstblockpractice.presentation.fragments.search.SEARCH_BY_EVENT_TAG
import com.ps_pn.firstblockpractice.presentation.fragments.search.SEARCH_BY_ORG_TAG
import com.ps_pn.firstblockpractice.presentation.models.Event
import com.ps_pn.firstblockpractice.presentation.models.Filter
import com.ps_pn.firstblockpractice.presentation.models.Friend
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow

object StubData {

    private var categoriesData = listOf<CategoryAdapterEntity>()
    var categoriesIsLoaded = MutableLiveData(false)
    var newsIsLoaded = MutableLiveData(false)
    var newsData = listOf<Event>()

    private val _searchedDataByEvent = MutableStateFlow<List<SearchResultEntity>?>(null)
    val searchedDataByEvent: StateFlow<List<SearchResultEntity>?> = _searchedDataByEvent

    private val _searchedDataByOrg = MutableStateFlow<List<SearchResultEntity>?>(null)
    val searchedDataByOrg: StateFlow<List<SearchResultEntity>?> = _searchedDataByOrg

    private val _budgeFlow = MutableStateFlow(0)
    val budgeFlow: StateFlow<Int> = _budgeFlow

    fun getFriendsStubData(): List<Friend> {
        val friends = mutableListOf<Friend>()
        friends.add(Friend(id = 1, name = "Дмитрий Валерьевич", img = ""))
        friends.add(Friend(id = 2, name = "Евгений Александров", img = ""))
        friends.add(Friend(id = 3, name = "Виктор Кузнецов", img = ""))
        return friends
    }

    fun loadFromStorage(): List<CategoryAdapterEntity> {
        if (categoriesData.isNotEmpty()) {
            categoriesIsLoaded.postValue(true)
            return categoriesData
        }
        categoriesData = JSONParser.getCategoriesFromJson()
            .map { Mapper.mapJSONCategoryToPresentation(it) }
        categoriesIsLoaded.postValue(true)
        return categoriesData
    }

    fun getSearchResultsStubData(query: String, queryTag: Int) {
        when (queryTag) {
            SEARCH_BY_EVENT_TAG -> searchByEvent(query)
            SEARCH_BY_ORG_TAG -> searchByOrg(query)
        }
    }

    fun clearSearchedData(tag: Int) {
        when (tag) {
            SEARCH_BY_EVENT_TAG -> _searchedDataByEvent.value = null
            SEARCH_BY_ORG_TAG -> _searchedDataByOrg.value = null
        }
    }

    fun emitToBadge(value: Int) {
        _budgeFlow.value = value
    }

    private fun searchByEvent(query: String) {
        val value = newsData
            .filter { event -> event.label.contains(query, true) }
            .map { event -> SearchResultEntity(event.label) }
        _searchedDataByEvent.value = value
    }

    private fun searchByOrg(query: String) {
        val value = newsData
            .filter { event -> event.company.contains(query, true) }
            .map { event -> SearchResultEntity(event.label) }
        _searchedDataByOrg.value = value
    }

    fun getNewsEventsStubData(): List<Event> {
        if (newsIsLoaded.value == true) {
            return newsData
        }
        newsData = JSONParser.getNewsFromJson().toMutableList()
            .map { Mapper.mapJSONEventToPresentation(it) }
        newsIsLoaded.postValue(true)
        _budgeFlow.value = newsData.size
        return newsData
    }

    fun filterNewsEventsStubData(
        currentList: List<Event>,
        filterCategories: List<Filter>
    ): List<Event> {
        val activeFilters = filterCategories.filter { it.isActive }.map { it.id }
        if (activeFilters.isEmpty()) {
            return emptyList()
        }
        val result = mutableListOf<Event>()
        for (news in currentList) {
            val filtersInNewsItem = news.categories.map { it.id }
            for (filterInItem in filtersInNewsItem) {
                if (activeFilters.any { activeFilter -> activeFilter == filterInItem }) {
                    result.add(news)
                    break
                }
            }
        }
        return result
    }

    fun getCategories(): Flow<List<CategoryAdapterEntity>> = flow {
        emit(ApiFactory.apiService.getCategories().map { Mapper.mapDtoCategoryToPresentation(it) })
    }

    fun getFriends(): Flow<List<Friend>> = flow {
        emit(ApiFactory.apiService.getFriends().map { Mapper.mapDtoFriendToPresentation(it) })
    }
}

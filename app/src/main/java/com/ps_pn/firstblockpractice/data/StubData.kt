package com.ps_pn.firstblockpractice.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ps_pn.firstblockpractice.data.db.AppDataBase
import com.ps_pn.firstblockpractice.data.network.ApiFactory
import com.ps_pn.firstblockpractice.data.network.NoConnectivityException
import com.ps_pn.firstblockpractice.presentation.App
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

    private val dao = AppDataBase.getInstance(App.instance).appDao()
    private val apiService = ApiFactory.apiService

    var categoriesIsLoaded = MutableLiveData(false)
    var eventsIsLoaded = MutableLiveData(false)
    var newsData = listOf<Event>()

    val events = loadEvents()
    val categories = loadCategories()

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

    fun getSearchResults(query: String, queryTag: Int) {
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

    fun filterEvents(
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

    private fun loadCategories() = flow {
        if (categoriesIsLoaded.value == true) {
            emit(getCategoriesFromDb())
            return@flow
        }
        try {
            val response = apiService.getCategories()
            if (response.isSuccessful) {
                if (!response.body().isNullOrEmpty()) {
                    val categoriesDto = response.body().orEmpty()
                    dao.insertCategories(categoriesDto.map { Mapper.mapDtoToDbModel(it) })
                    categoriesIsLoaded.postValue(true)
                    emit(getCategoriesFromDb())
                    return@flow
                }
            } else {
                Log.i("TestLOG", "responseError " + response.errorBody().toString())
            }
        } catch (networkException: NoConnectivityException) {
            Log.i("TestLOG", networkException.toString())
        }
        emit(loadCategoryFromStorage())
    }

    private fun loadEvents() = flow {
        if (eventsIsLoaded.value == true) {
            emit(getEventsFromDb())
            return@flow
        }
        try {
            val response = apiService.getEvents()
            if (response.isSuccessful) {
                if (!response.body().isNullOrEmpty()) {
                    val eventDto = response.body().orEmpty()
                    dao.insertEvents(eventDto.map { Mapper.mapDtoToDbModel(it) })
                    emit(getEventsFromDb())
                    eventsIsLoaded.postValue(true)
                    return@flow
                }
            } else {
                Log.i("TestLOG", "responseError " + response.errorBody().toString())
            }
        } catch (networkException: NoConnectivityException) {
            Log.i("TestLOG", networkException.toString())
        }
        emit(loadEventsFromStorage())
    }

    suspend fun markIsReadEvent(id: Int) {
        dao.markAsReadEvent(id)
    }

    private suspend fun loadEventsFromStorage(): List<Event> {
        val events = JSONParser.getEventsFromJson().map { Mapper.mapJSONEventToDb(it) }
        dao.insertEvents(events)
        eventsIsLoaded.postValue(true)
        return dao.getEvents().map { Mapper.mapDbToPresentation(it) }
    }


    private fun getCategoriesFromDb(): List<CategoryAdapterEntity> {
        return dao.getCategories().map { Mapper.mapDbToPresentation(it) }
    }

    private fun getEventsFromDb(): List<Event> {
        return dao.getEvents().map { Mapper.mapDbToPresentation(it) }
    }

    private suspend fun loadCategoryFromStorage(): List<CategoryAdapterEntity> {
        val categories = JSONParser.getCategoriesFromJson().map { Mapper.mapJSONCategoryToDb(it) }
        dao.insertCategories(categories)
        categoriesIsLoaded.postValue(true)
        return dao.getCategories().map { Mapper.mapDbToPresentation(it) }
    }

    fun getFriends(): Flow<List<Friend>> = flow {
        emit(ApiFactory.apiService.getFriends().map { Mapper.mapDtoFriendToPresentation(it) })
    }
}


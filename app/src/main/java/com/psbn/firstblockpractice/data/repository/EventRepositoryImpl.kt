package com.psbn.firstblockpractice.data.repository


import android.util.Log
import com.psbn.firstblockpractice.core.data.db.AppDao
import com.psbn.firstblockpractice.core.data.jsonstorage.JSONParser
import com.psbn.firstblockpractice.core.data.network.ApiService
import com.psbn.firstblockpractice.core.data.network.NoConnectivityException
import com.psbn.firstblockpractice.core.date.mapper.EventMapper
import com.psbn.firstblockpractice.domain.news.entity.Event
import com.psbn.firstblockpractice.domain.news.repository.EventRepository
import com.psbn.firstblockpractice.presentation.utills.prefmanager.filter.FilterPreferenceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val jsonParser: JSONParser,
    private val mapper: EventMapper,
    private val dao: AppDao,
    private val apiService: ApiService
) : EventRepository {

    override fun getEvents(): Flow<List<Event>> {
        return loadEvents()
    }

    override suspend fun readEvent(id: Int) {
        dao.markAsReadEvent(id)
    }

    private fun filterEvents(currentList: List<Event>): List<Event> {
        val activeFilters = FilterPreferenceManager.filterList.filter { it.isActive }.map { it.id }
        if (activeFilters.isEmpty()) {
            return emptyList()
        }
        val result = mutableListOf<Event>()
        currentList.forEach { event ->
            val filters = event.categories.map { it.id }
            filters.forEach { filterOfEvent ->
                if (activeFilters.any { activeFilter -> activeFilter == filterOfEvent }) {
                    result.add(event)
                }
            }
        }
        return result
    }

    private fun loadEvents() = flow {
        val dataFromDb = getEventsFromDb()
        if (dataFromDb.isNotEmpty()) {
            emit(filterEvents(dataFromDb))
            return@flow
        }
        try {
            val response = apiService.getEvents()
            if (response.isSuccessful) {
                if (!response.body().isNullOrEmpty()) {
                    val eventDto = response.body().orEmpty()
                    dao.insertEvents(eventDto.map { mapper.mapDtoToDbModel(it) })
                    emit(filterEvents(getEventsFromDb()))
                    return@flow
                }
            } else {
                Log.i("TestLOG", "responseError " + response.errorBody().toString())
            }
        } catch (networkException: NoConnectivityException) {
            Log.i("TestLOG", networkException.toString())
        }
        emit(filterEvents(loadEventsFromStorage()))
    }

    private suspend fun loadEventsFromStorage(): List<Event> {
        val events = jsonParser.getEventsFromJson().map { mapper.mapJSONEventToDb(it) }
        dao.insertEvents(events)
        return dao.getEvents().map { mapper.mapDbToDomain(it) }
    }

    private fun getEventsFromDb(): List<Event> {
        return dao.getEvents().map { mapper.mapDbToDomain(it) }
    }
}

package com.psbn.search.data.repository

import com.psbn.firstblockpractice.core.data.db.AppDao
import com.psbn.firstblockpractice.core.data.db.entity.EventDbModel
import com.psbn.search.data.mapper.EventMapper
import com.psbn.search.domain.entity.Event
import com.psbn.search.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val mapper: EventMapper,
    private val dao: AppDao,
) : SearchRepository {

    private var events: List<EventDbModel> = emptyList()

    override suspend fun searchEventByEvent(query: String): List<Event> {
        checkEvents()
        return events.filter { event -> event.label.contains(query, true) }
            .map { mapper.mapDbToDomain(it) }
    }

    override suspend fun searchEventByOrg(query: String): List<Event> {
        checkEvents()
        return events.filter { event -> event.company.contains(query, true) }
            .map { mapper.mapDbToDomain(it) }
    }

    private fun checkEvents() {
        if (events.isEmpty()) {
            events = dao.getEvents()
        }
    }
}

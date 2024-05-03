package com.ps_pn.firstblockpractice.data.repository

import com.ps_pn.firstblockpractice.data.db.AppDao
import com.ps_pn.firstblockpractice.data.db.entity.EventDbModel
import com.ps_pn.firstblockpractice.data.mapper.EventMapper
import com.ps_pn.firstblockpractice.domain.news.entity.Event
import com.ps_pn.firstblockpractice.domain.search.repository.SearchRepository
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

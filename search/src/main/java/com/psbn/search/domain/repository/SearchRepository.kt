package com.psbn.search.domain.repository

import com.psbn.search.domain.entity.Event

interface SearchRepository {
    suspend fun searchEventByEvent(query: String): List<Event>
    suspend fun searchEventByOrg(query: String): List<Event>
}

package com.psbn.news.domain.repository

import com.psbn.news.domain.entity.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    fun getEvents(): Flow<List<Event>>

    suspend fun readEvent(id: Int)
}

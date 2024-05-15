package com.psbn.firstblockpractice.domain.news.repository

import com.psbn.firstblockpractice.domain.news.entity.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    fun getEvents(): Flow<List<Event>>

    suspend fun readEvent(id: Int)
}

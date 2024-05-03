package com.ps_pn.firstblockpractice.domain.news.repository

import com.ps_pn.firstblockpractice.domain.news.entity.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    fun getEvents(): Flow<List<Event>>

    suspend fun readEvent(id: Int)
}

package com.ps_pn.firstblockpractice.domain.repository

import com.ps_pn.firstblockpractice.domain.entity.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    fun getEvents(): Flow<List<Event>>

    suspend fun readEvent(id: Int)
}

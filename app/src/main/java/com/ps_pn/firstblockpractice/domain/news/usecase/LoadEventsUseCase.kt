package com.ps_pn.firstblockpractice.domain.news.usecase

import com.ps_pn.firstblockpractice.domain.news.entity.Event
import com.ps_pn.firstblockpractice.domain.news.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadEventsUseCase @Inject constructor(private val repository: EventRepository) {
    operator fun invoke(): Flow<List<Event>> {
        return repository.getEvents()
    }
}

package com.ps_pn.firstblockpractice.domain.usecases

import com.ps_pn.firstblockpractice.domain.entity.Event
import com.ps_pn.firstblockpractice.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadEventsUseCase @Inject constructor(private val repository: EventRepository) {
    operator fun invoke(): Flow<List<Event>> {
        return repository.getEvents()
    }
}

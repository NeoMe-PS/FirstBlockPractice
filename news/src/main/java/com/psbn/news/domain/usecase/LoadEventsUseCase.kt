package com.psbn.news.domain.usecase

import com.psbn.news.domain.entity.Event
import com.psbn.news.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadEventsUseCase @Inject constructor(private val repository: EventRepository) {
    operator fun invoke(): Flow<List<Event>> {
        return repository.getEvents()
    }
}

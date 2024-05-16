package com.psbn.news.domain.usecase

import com.psbn.news.domain.repository.EventRepository
import javax.inject.Inject

class ReadEventUseCase @Inject constructor(private val repository: EventRepository) {
    suspend operator fun invoke(id: Int) {
        repository.readEvent(id)
    }
}

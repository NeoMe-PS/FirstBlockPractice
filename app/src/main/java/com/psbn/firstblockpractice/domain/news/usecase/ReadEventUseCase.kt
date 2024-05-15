package com.psbn.firstblockpractice.domain.news.usecase

import com.psbn.firstblockpractice.domain.news.repository.EventRepository
import javax.inject.Inject

class ReadEventUseCase @Inject constructor(private val repository: EventRepository) {
    suspend operator fun invoke(id: Int) {
        repository.readEvent(id)
    }
}

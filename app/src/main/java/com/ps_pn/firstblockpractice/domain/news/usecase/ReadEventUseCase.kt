package com.ps_pn.firstblockpractice.domain.news.usecase

import com.ps_pn.firstblockpractice.domain.news.repository.EventRepository
import javax.inject.Inject

class ReadEventUseCase @Inject constructor(private val repository: EventRepository) {
    suspend operator fun invoke(id: Int) {
        repository.readEvent(id)
    }
}

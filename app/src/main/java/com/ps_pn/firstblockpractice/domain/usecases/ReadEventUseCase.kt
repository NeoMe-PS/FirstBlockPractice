package com.ps_pn.firstblockpractice.domain.usecases

import com.ps_pn.firstblockpractice.domain.repository.EventRepository
import javax.inject.Inject

class ReadEventUseCase @Inject constructor(private val repository: EventRepository) {
    suspend operator fun invoke(id: Int) {
        repository.readEvent(id)
    }
}

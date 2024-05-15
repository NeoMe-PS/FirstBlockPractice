package com.psbn.firstblockpractice.domain.search.usecase

import com.psbn.firstblockpractice.domain.search.repository.SearchRepository
import javax.inject.Inject

class SearchEventByEventUseCase @Inject constructor(private val repository: SearchRepository) {
    suspend operator fun invoke(search: String) = repository.searchEventByEvent(search)
}


package com.psbn.search.domain.usecase

import com.psbn.search.domain.repository.SearchRepository
import javax.inject.Inject

class SearchEventByOrgUseCase @Inject constructor(private val repository: SearchRepository) {
    suspend operator fun invoke(search: String) = repository.searchEventByOrg(search)
}


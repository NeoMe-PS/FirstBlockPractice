package com.ps_pn.firstblockpractice.domain.search.usecase

import com.ps_pn.firstblockpractice.domain.search.repository.SearchRepository
import javax.inject.Inject

class SearchEventByOrgUseCase @Inject constructor(private val repository: SearchRepository) {
    suspend operator fun invoke(search: String) = repository.searchEventByOrg(search)
}


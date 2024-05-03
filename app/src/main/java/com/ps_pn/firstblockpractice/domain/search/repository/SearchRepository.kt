package com.ps_pn.firstblockpractice.domain.search.repository

import com.ps_pn.firstblockpractice.domain.news.entity.Event

interface SearchRepository {
    suspend fun searchEventByEvent(query: String): List<Event>
    suspend fun searchEventByOrg(query: String): List<Event>
}

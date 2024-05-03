package com.ps_pn.firstblockpractice.domain.repository

import com.ps_pn.firstblockpractice.domain.entity.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<List<Category>>
}

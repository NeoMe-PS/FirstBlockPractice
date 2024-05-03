package com.ps_pn.firstblockpractice.domain.help.repository

import com.ps_pn.firstblockpractice.domain.help.entity.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<List<Category>>
}

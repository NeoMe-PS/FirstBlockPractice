package com.psbn.firstblockpractice.help.domain.repository

import com.psbn.firstblockpractice.help.domain.entity.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<List<Category>>
}

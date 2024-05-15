package com.psbn.firstblockpractice.help.domain.usecase

import com.psbn.firstblockpractice.help.domain.entity.Category
import com.psbn.firstblockpractice.help.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadCategoriesUseCase @Inject constructor(private val repository: CategoryRepository) {
    operator fun invoke(): Flow<List<Category>> {
        return repository.getCategories()
    }
}

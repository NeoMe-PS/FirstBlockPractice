package com.ps_pn.firstblockpractice.domain.help.usecase

import com.ps_pn.firstblockpractice.domain.help.entity.Category
import com.ps_pn.firstblockpractice.domain.help.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadCategoriesUseCase @Inject constructor(private val repository: CategoryRepository) {
    operator fun invoke(): Flow<List<Category>> {
        return repository.getCategories()
    }
}

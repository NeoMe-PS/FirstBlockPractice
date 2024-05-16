package com.psbn.firstblockpractice.help.data

import android.util.Log
import com.psbn.firstblockpractice.core.data.db.AppDao
import com.psbn.firstblockpractice.core.data.jsonstorage.JSONParser
import com.psbn.firstblockpractice.core.data.network.ApiService
import com.psbn.firstblockpractice.core.data.network.NoConnectivityException
import com.psbn.firstblockpractice.help.data.mapper.CategoryMapper
import com.psbn.firstblockpractice.help.domain.entity.Category
import com.psbn.firstblockpractice.help.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val jsonParser: JSONParser,
    private val mapper: CategoryMapper,
    private val dao: AppDao,
    private val apiService: ApiService
) : CategoryRepository {

    private fun loadCategories() = flow {
        val dataFromDb = loadCategoriesFromDb()
        if (dataFromDb.isNotEmpty()) {
            emit(dataFromDb)
            return@flow
        }
        try {
            val response = apiService.getCategories()
            if (response.isSuccessful) {
                if (!response.body().isNullOrEmpty()) {
                    val categoriesDto = response.body().orEmpty()
                    dao.insertCategories(categoriesDto.map { mapper.mapDtoToDbModel(it) })
                    emit(loadCategoriesFromDb())
                    return@flow
                }
            } else {
                Log.i("TestLOG", "responseError " + response.errorBody().toString())
            }
        } catch (networkException: NoConnectivityException) {
            Log.i("TestLOG", networkException.toString())
        }
        emit(loadCategoryFromStorage())
    }

    private fun loadCategoriesFromDb(): List<Category> {
        return dao.getCategories().map { mapper.mapDbToPresentation(it) }
    }

    private suspend fun loadCategoryFromStorage(): List<Category> {
        val categories =
            jsonParser.getCategoriesFromJson().map { mapper.mapJSONCategoryToDb(it) }
        dao.insertCategories(categories)
        return dao.getCategories().map { mapper.mapDbToPresentation(it) }
    }

    override fun getCategories(): Flow<List<Category>> {
        return loadCategories()
    }
}

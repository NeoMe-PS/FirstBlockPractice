package com.ps_pn.firstblockpractice.data.mapper

import com.ps_pn.firstblockpractice.R
import com.ps_pn.firstblockpractice.data.db.entity.CategoryDbModel
import com.ps_pn.firstblockpractice.data.jsonstorage.CategoryJSON
import com.ps_pn.firstblockpractice.data.network.dto.CategoryDto
import com.ps_pn.firstblockpractice.domain.entity.Category
import com.ps_pn.firstblockpractice.presentation.models.Filter
import javax.inject.Inject

class CategoryMapper @Inject constructor() {
    fun mapDbToPresentation(categoryDbModel: CategoryDbModel) =
        Category(
            id = categoryDbModel.id,
            label = categoryDbModel.label,
            img = getCategoryResId(categoryDbModel.id)
        )

    fun mapJSONCategoryToDb(categoryJSON: CategoryJSON) = CategoryDbModel(
        id = categoryJSON.id,
        label = categoryJSON.label,
        img = getCategoryResId(categoryJSON.id)
    )

    fun mapDtoToDbModel(categoryDto: CategoryDto) = CategoryDbModel(
        id = categoryDto.id,
        label = categoryDto.label,
        img = getCategoryResId(categoryDto.id)
    )

    private fun getCategoryResId(idFrom: Int): String = when (idFrom) {
        Filter.Kids.id -> R.drawable.icon_kids.toString()
        Filter.Adults.id -> R.drawable.icon_adult.toString()
        Filter.Elderly.id -> R.drawable.icon_elderly.toString()
        Filter.Animals.id -> R.drawable.icon_animals.toString()
        Filter.Events.id -> R.drawable.icon_event.toString()
        else -> R.drawable.bg_white_rounded.toString()
    }
}

package com.psbn.firstblockpractice.help.data.mapper

import com.psbn.firstblockpractice.core.data.db.entity.CategoryDbModel
import com.psbn.firstblockpractice.core.data.jsonstorage.models.CategoryJSON
import com.psbn.firstblockpractice.core.data.network.dto.CategoryDto
import com.psbn.firstblockpractice.help.domain.entity.Category
import javax.inject.Inject

class CategoryMapper @Inject constructor() {
    fun mapDbToPresentation(categoryDbModel: CategoryDbModel) =
        Category(
            id = categoryDbModel.id,
            label = categoryDbModel.label,
            img = getCategoryResId(categoryDbModel.id)
        )

    fun mapJSONCategoryToDb(categoryJSON: CategoryJSON) =
        CategoryDbModel(
            id = categoryJSON.id,
            label = categoryJSON.label,
            img = getCategoryResId(categoryJSON.id)
        )

    fun mapDtoToDbModel(categoryDto: CategoryDto) =
        CategoryDbModel(
            id = categoryDto.id,
            label = categoryDto.label,
            img = getCategoryResId(categoryDto.id)
        )

    private fun getCategoryResId(idFrom: Int): String = when (idFrom) {
        KIDS_ID -> com.ps_pn.firstblockpractice.help.R.drawable.icon_kids.toString()
        ADULTS_ID -> com.ps_pn.firstblockpractice.help.R.drawable.icon_adult.toString()
        ELDERLY_ID -> com.ps_pn.firstblockpractice.help.R.drawable.icon_elderly.toString()
        ANIMALS_ID -> com.ps_pn.firstblockpractice.help.R.drawable.icon_animals.toString()
        EVENTS_ID -> com.ps_pn.firstblockpractice.help.R.drawable.icon_event.toString()
        else -> com.ps_pn.firstblockpractice.help.R.drawable.round_button.toString()
    }

    companion object {
        const val KIDS_ID = 1
        const val ADULTS_ID = 2
        const val ELDERLY_ID = 3
        const val ANIMALS_ID = 4
        const val EVENTS_ID = 5
    }
}

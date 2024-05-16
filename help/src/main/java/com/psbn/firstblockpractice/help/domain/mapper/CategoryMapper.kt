package com.psbn.firstblockpractice.help.domain.mapper

import com.psbn.firstblockpractice.help.domain.entity.Category
import com.psbn.firstblockpractice.help.presentation.model.HelpCategory

object CategoryMapper {
    fun mapDomainToUi(category: Category) = HelpCategory(
        id = category.id,
        label = category.label,
        img = category.img
    )
}
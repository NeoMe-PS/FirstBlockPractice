package com.ps_pn.firstblockpractice.domain.help.mapper

import com.ps_pn.firstblockpractice.domain.help.entity.Category
import com.ps_pn.firstblockpractice.presentation.models.CategoryUI

object CategoryMapper {
    fun mapDomainToUi(category: Category) = CategoryUI(
        id = category.id,
        label = category.label,
        img = category.img
    )
}
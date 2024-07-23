package com.psbn.firstblockpractice.help.data.mapper

import com.psbn.firstblockpractice.core.data.db.entity.CategoryDbModel
import com.psbn.firstblockpractice.core.data.jsonstorage.models.CategoryJSON
import com.psbn.firstblockpractice.core.data.network.dto.CategoryDto
import com.psbn.firstblockpractice.help.domain.entity.Category

val expectedDb = CategoryDbModel(
    id = 1,
    label = "label",
    img = "2131230852"
)
val expectedDomain = Category(
    id = 1,
    label = "label",
    img = "2131230852"
)
val inputJSON = CategoryJSON(
    id = 1,
    label = "label",
    img = "1"
)
val inputDto = CategoryDto(
    id = 1,
    label = "label",
    img = "1"
)
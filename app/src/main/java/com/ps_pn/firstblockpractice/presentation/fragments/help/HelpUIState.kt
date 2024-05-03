package com.ps_pn.firstblockpractice.presentation.fragments.help

import com.ps_pn.firstblockpractice.domain.entity.Category

sealed class HelpUIState {
    object Loading : HelpUIState()
    object Error : HelpUIState()
    data class Response(val categories: List<Category>) : HelpUIState()
}

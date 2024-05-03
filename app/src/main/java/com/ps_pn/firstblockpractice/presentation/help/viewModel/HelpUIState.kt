package com.ps_pn.firstblockpractice.presentation.help.viewModel

import com.ps_pn.firstblockpractice.domain.help.entity.Category

sealed class HelpUIState {
    object Loading : HelpUIState()
    object Error : HelpUIState()
    data class Response(val categories: List<Category>) : HelpUIState()
}

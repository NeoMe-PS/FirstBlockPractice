package com.psbn.firstblockpractice.help.presentation.viewModel

import com.psbn.firstblockpractice.help.domain.entity.Category

sealed class HelpUIState {
    object Loading : HelpUIState()
    object Error : HelpUIState()
    data class Response(val categories: List<Category>) : HelpUIState()
}

package com.ps_pn.firstblockpractice.presentation.help.viewModel

import androidx.lifecycle.ViewModel
import com.ps_pn.firstblockpractice.domain.help.usecase.LoadCategoriesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class HelpViewModel @Inject constructor(
    private val loadCategoriesUseCase: LoadCategoriesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HelpUIState>(HelpUIState.Loading)
    val uiState: StateFlow<HelpUIState> = _uiState
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    init {
        setState()
    }

    private fun setState() {
        scope.launch {
            loadCategoriesUseCase.invoke()
                .map { list -> HelpUIState.Response(list) as HelpUIState }
                .onStart { emit(HelpUIState.Loading) }
                .collect { state ->
                    _uiState.value = state
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}

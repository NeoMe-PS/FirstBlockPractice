package com.psbn.firstblockpractice.help.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.psbn.firstblockpractice.help.domain.usecase.LoadCategoriesUseCase
import javax.inject.Inject

class HelpViewModelFactory @Inject constructor(
    private val loadCategoriesUseCase: LoadCategoriesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(HelpViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HelpViewModel(
                loadCategoriesUseCase = loadCategoriesUseCase,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
        return super.create(modelClass, extras)
    }

}
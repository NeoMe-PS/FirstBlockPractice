package com.psbn.firstblockpractice.help.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.psbn.firstblockpractice.help.di.HelpScope
import javax.inject.Inject
import javax.inject.Provider

@HelpScope
class HelpViewModelFactory @Inject constructor(
    private val factories: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return factories[modelClass]?.get() as T
    }
}
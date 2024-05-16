package com.psbn.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.psbn.search.di.SearchScope
import javax.inject.Inject
import javax.inject.Provider

@SearchScope
class SearchViewModelFactory @Inject constructor(
    private val factories: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return factories[modelClass]?.get() as T
    }
}

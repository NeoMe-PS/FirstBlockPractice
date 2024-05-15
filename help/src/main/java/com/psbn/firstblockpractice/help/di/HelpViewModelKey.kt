package com.psbn.firstblockpractice.help.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class HelpViewModelKey(val value: KClass<out ViewModel>)
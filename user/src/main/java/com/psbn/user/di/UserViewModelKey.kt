package com.psbn.user.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class UserViewModelKey(val value: KClass<out ViewModel>)
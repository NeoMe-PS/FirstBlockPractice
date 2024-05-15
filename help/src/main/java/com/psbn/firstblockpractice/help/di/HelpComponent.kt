package com.psbn.firstblockpractice.help.di

import android.app.Application
import com.psbn.firstblockpractice.help.presentation.ui.HelpFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Component.Factory

@Component(modules = [HelpDataModule::class], dependencies = [HelpDeps::class])
interface HelpComponent {

    fun inject(helpFragment: HelpFragment)

    @Factory
    interface HelpComponentFactory {

        fun create(@BindsInstance application: Application, helpDeps: HelpDeps): HelpComponent
    }
}
package com.psbn.user.di

import com.psbn.user.presentation.ui.EditProfileFragment
import com.psbn.user.presentation.ui.UserProfileFragment
import dagger.Component
import dagger.Component.Factory

@UserScope
@Component(
    modules = [UserViewModelModule::class, UserDataModule::class],
    dependencies = [UserDeps::class]
)
interface UserComponent {

    fun inject(userProfileFragment: UserProfileFragment)
    fun inject(editProfileFragment: EditProfileFragment)

    @Factory
    interface UserFactory {
        fun create(userDeps: UserDeps): UserComponent
    }
}
package com.codepunk.jetpack.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codepunk.jetpack.ui.user.UserViewModel
import com.codepunk.jetpack.viewmodel.JetpackViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindUserViewModel(userViewModel: UserViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: JetpackViewModelFactory): ViewModelProvider.Factory
}
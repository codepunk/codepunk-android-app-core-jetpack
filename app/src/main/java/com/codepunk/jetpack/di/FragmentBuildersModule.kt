package com.codepunk.jetpack.di

import com.codepunk.jetpack.ui.main.SecondaryFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeSecondaryFragment(): SecondaryFragment
}
package com.codepunk.jetpack.di

import android.app.Application
import com.codepunk.jetpack.JetpackApp
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(jetpackApp: JetpackApp)
}
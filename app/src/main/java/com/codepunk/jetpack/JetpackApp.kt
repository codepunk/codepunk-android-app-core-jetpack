package com.codepunk.jetpack

import android.app.Activity
import android.app.Application
import com.codepunk.jetpack.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/*
 * TODO NEXT: Get an accessToken so I can try to actually call getUser for real (!)
 */

class JetpackApp : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector
}
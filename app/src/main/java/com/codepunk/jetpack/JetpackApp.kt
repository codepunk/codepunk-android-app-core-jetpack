/*
 * Copyright (C) 2018 Codepunk, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codepunk.jetpack

import android.app.Activity
import android.app.Application
import com.codepunk.jetpack.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/*
 * TODO NEXT: Get an accessToken so I can try to actually call getUser for real (!)
 */

/**
 * The main Jetpack application.
 */
class JetpackApp : Application(), HasActivityInjector {

    // region Properties

    /**
     * The [DispatchingAndroidInjector] that this application will use for dependency
     * injection.
     */
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    // endregion Properties

    // region Lifecycle methods

    /**
     * Injects dependencies into this application instance.
     */
    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }

    // endregion Lifecycle methods

    // region Implemented methods

    /**
     * Implementation of [HasActivityInjector.activityInjector]. Supplies an
     * [AndroidInjector] for dependency injection into [Activity] instances.
     */
    override fun activityInjector() = dispatchingAndroidInjector

    // endregion Implemented methods
}
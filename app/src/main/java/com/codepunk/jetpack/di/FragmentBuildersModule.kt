/*
 * Copyright (C) 2017 The Android Open Source Project
 * Modifications Copyright (C) 2018 Codepunk, LLC
 *               Author(s): Scott Slater
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
 *
 *
 * The original work can be found at The Android Open Source Project at
 *
 *      https://github.com/googlesamples
 *
 *      Repository:
 *      android-architecture-components
 *
 *      File:
 *      GithubBrowserSample/app/src/main/java/com/android/example/github/di/FragmentBuildersModule.kt
 *
 * Modifications:
 * August 2018: Jetpack-specific dependency creation; code organization and comments
 */

package com.codepunk.jetpack.di

import com.codepunk.jetpack.ui.main.SecondaryFragment
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector

/**
 * Generates an [AndroidInjector] for Jetpack fragments.
 */
@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    // region Methods

    @ContributesAndroidInjector
    abstract fun contributeSecondaryFragment(): SecondaryFragment

    // endregion Methods
}

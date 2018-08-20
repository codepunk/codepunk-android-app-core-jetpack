/*
 * Copyright (C) 2017 The Android Open Source Project
 * Modifications (C) 2018 Codepunk, LLC
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
 * In the following location:
 *
 *      android-architecture-components/GithubBrowserSample/app/src/main/java/com/android/example/github/viewmodel/GithubViewModelFactory
 *
 * Modifications:
 * August 2018: Some reformatting for better readability
 */

package com.codepunk.jetpack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class JetpackViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    // region Implemented methods

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val creator = creators[modelClass]
            ?: creators.entries.firstOrNull { entry ->
                modelClass.isAssignableFrom(entry.key)
            }?.value ?: throw IllegalArgumentException("Unknown model class $modelClass")
        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    // endregion Implemented methods
}
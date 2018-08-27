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
 *      GithubBrowserSample/app/src/main/java/com/android/example/github/vo/Resource.kt
 *
 * Modifications:
 * August 2018: Code organization and comments
 */

package com.codepunk.jetpack.vo

import com.codepunk.jetpack.vo.Status.ERROR
import com.codepunk.jetpack.vo.Status.LOADING
import com.codepunk.jetpack.vo.Status.SUCCESS

// TODO Look into making this a sealed class?

/**
 * A generic class that holds a value with its loading status.
 */
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    // region Companion object

    companion object {

        // region Methods

        /**
         * Creates a [Resource] with a status of [SUCCESS].
         */
        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        /**
         * Creates a [Resource] with a status of [ERROR].
         */
        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(ERROR, data, msg)
        }

        /**
         * Creates a [Resource] with a status of [LOADING].
         */
        fun <T> loading(data: T?): Resource<T> {
            return Resource(LOADING, data, null)
        }

        // endregion Methods

    }

    // endregion Companion object
}


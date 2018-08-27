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
 *      GithubBrowserSample/app/src/main/java/com/android/example/github/api/ApiResponse.kt
 *
 * Modifications:
 * August 2018: Code organization and comments
 */

package com.codepunk.jetpack.api

import android.util.Log
import retrofit2.Response
import java.util.regex.Pattern

/**
 * Common class used by API responses.
 */
@Suppress("unused") // T is used in extending classes
sealed class ApiResponse<T> {

    // region Companion object

    companion object {

        // region Methods

        /**
         * Creates an [ApiErrorResponse] instance.
         */
        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(error.message ?: "unknown error")
        }

        /**
         * Creates an [ApiResponse] instance appropriate to the supplied response.
         */
        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(
                        body = body,
                        linkHeader = response.headers()?.get("link")
                    )
                }
            } else {
                val msg = response.errorBody()?.string()
                val errorMsg = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }
                ApiErrorResponse(errorMsg ?: "unknown error")
            }
        }

        // endregion Methods
    }

    // endregion Companion object
}

/**
 * A separate class for HTTP 204 resposes so that we can make ApiSuccessResponse's body non-null.
 */
class ApiEmptyResponse<T> : ApiResponse<T>()

/**
 * An [ApiResponse] class representing a successful API call.
 */
data class ApiSuccessResponse<T>(
    /**
     * An instance of class [T] representing the body of the successful API call.
     */
    val body: T,

    /**
     * A [Map] of links contained in the headers of this successful API call.
     */
    val links: Map<String, String>
) : ApiResponse<T>() {

    // region Constructors

    constructor(body: T, linkHeader: String?) : this(
        body = body,
        links = linkHeader?.extractLinks() ?: emptyMap()
    )

    // endregion Constructors

    // region Properties

    /**
     * Processes paging in successful API responses.
     */
    val nextPage: Int? by lazy(LazyThreadSafetyMode.NONE) {
        links[NEXT_LINK]?.let { next ->
            val matcher = PAGE_PATTERN.matcher(next)
            if (!matcher.find() || matcher.groupCount() != 1) {
                null
            } else {
                try {
                    Integer.parseInt(matcher.group(1))
                } catch (ex: NumberFormatException) {
                    Log.w(
                        javaClass.simpleName,
                        "cannot parse next page from $next"
                    ) // TODO Loginator
                    null
                }
            }
        }
    }

    // endregion Properties

    // region Companion object

    companion object {

        // region Constants

        /**
         * A [Pattern] that matches a link.
         */
        private val LINK_PATTERN = Pattern.compile("<([^>]*)>[\\s]*;[\\s]*rel=\"([a-zA-Z0-9]+)\"")

        /**
         * A [Pattern] that matches a page field in a URL query.
         */
        private val PAGE_PATTERN = Pattern.compile("\\bpage=(\\d+)")

        /**
         * A constant used to get the "next" link out of the [links] map if it exists. Used for
         * paging purposes.
         */
        private const val NEXT_LINK = "next"

        // endregion Constants

        // region Methods

        /**
         * Extension function that extracts links from a string and stores them in a [Map].
         */
        private fun String.extractLinks(): Map<String, String> {
            val links = mutableMapOf<String, String>()
            val matcher = LINK_PATTERN.matcher(this)

            while (matcher.find()) {
                val count = matcher.groupCount()
                if (count == 2) {
                    links[matcher.group(2)] = matcher.group(1)
                }
            }
            return links
        }

        // endregion Methods

    }

    // endregion Companion object
}

/**
 * [ApiResponse] class that represents an API error, storing the [errorMessage] associated with
 * the error.
 */
data class ApiErrorResponse<T>(val errorMessage: String) : ApiResponse<T>()

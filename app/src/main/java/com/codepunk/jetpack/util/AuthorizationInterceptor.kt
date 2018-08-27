/*
 * Copyright (C) 2018 Codepunk, LLC
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
 */

package com.codepunk.jetpack.util

import com.codepunk.jetpack.api.ACCESS_TOKEN_PLACEHOLDER
import com.codepunk.jetpack.api.AUTHORIZATION
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Singleton class that intercepts Retrofit requests and looks for a header with a name
 * of [AUTHORIZATION] ("Authorization"). If found, any instance in the value matching
 * [ACCESS_TOKEN_PLACEHOLDER] will be replaced with the current value of [accessToken].
 */
object AuthorizationInterceptor : Interceptor {

    // region Properties

    /**
     * The access token corresponding to the current session.
     * TODO Replace this with some sort of Session object (or just set the current Account)
     */
    var accessToken: String = ""

    // endregion Properties

    // region Implemented methods

    /**
     * Implements [Interceptor.intercept]. Looks for a header with a name of [AUTHORIZATION]
     * ("Authorization") and replaces any instance of [ACCESS_TOKEN_PLACEHOLDER] in the value with
     * the current value of [accessToken].
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(
            request.header(AUTHORIZATION)?.let { value ->
                request.newBuilder()
                    .header(
                        AUTHORIZATION,
                        value.replace(ACCESS_TOKEN_PLACEHOLDER, accessToken, true)
                    )
                    .build()
            } ?: request
        )
    }

    // endregion Implemented methods

}

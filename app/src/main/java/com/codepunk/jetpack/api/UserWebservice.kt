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

package com.codepunk.jetpack.api

import androidx.lifecycle.LiveData
import com.codepunk.jetpack.vo.User
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * [User] REST API access points.
 */
interface UserWebservice {

    @GET("api/user")
    @Headers(ACCEPT_APPLICATION_JSON, AUTHORIZATION_BEARER)
    fun getUser(): LiveData<ApiResponse<User>>

}

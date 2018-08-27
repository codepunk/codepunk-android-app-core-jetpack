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
 *      GithubBrowserSample/app/src/main/java/com/android/example/github/repository/UserRepository.kt
 *
 * Modifications:
 * August 2018: Jetpack-specific dependency creation; code organization and comments
 */

package com.codepunk.jetpack.repository

import androidx.lifecycle.LiveData
import com.codepunk.jetpack.AppExecutors
import com.codepunk.jetpack.api.ApiResponse
import com.codepunk.jetpack.api.UserWebservice
import com.codepunk.jetpack.db.UserDao
import com.codepunk.jetpack.vo.Resource
import com.codepunk.jetpack.vo.User
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository that handles User objects.
 */
@Singleton
class UserRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val userDao: UserDao,
    private val userWebservice: UserWebservice
) {

    // region Methods

    /**
     * Gets a user from the SQLite database based on [id], or from the network if the user with
     * the given id doesn't exist in the SQLite database.
     */
    fun getUser(id: Int): LiveData<Resource<User>> {
        return object : NetworkBoundResource<User, User>(appExecutors) {
            override fun saveCallResult(item: User) {
                userDao.insert(item)
            }

            override fun shouldFetch(data: User?) = data == null

            override fun loadFromDb() = userDao.getUser(id)

            override fun createCall(): LiveData<ApiResponse<User>> = userWebservice.getUser()
        }.asLiveData()
    }

    // endregion Methods

}
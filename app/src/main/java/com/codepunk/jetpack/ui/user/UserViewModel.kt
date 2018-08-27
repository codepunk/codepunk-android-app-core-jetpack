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
 *      GithubBrowserSample/app/src/main/java/com/android/example/github/ui/user/UserViewModel.kt
 *
 * Modifications:
 * August 2018: Code organization and comments
 */

package com.codepunk.jetpack.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.codepunk.jetpack.repository.UserRepository
import com.codepunk.jetpack.util.AbsentLiveData
import com.codepunk.jetpack.vo.Resource
import com.codepunk.jetpack.vo.User
import javax.inject.Inject

/**
 * A [ViewModel] for maintaining [User] objects.
 */
class UserViewModel @Inject constructor(userRepository: UserRepository) : ViewModel() {

    // region Properties

    /**
     * The id of the user.
     */
    private val _userId = MutableLiveData<Int>()
    val userId: MutableLiveData<Int>
        get() = _userId

    /**
     * The user instance. Will be an instance of [AbsentLiveData] if no userId is supplied, or the
     * result of [UserRepository.getUser] otherwise.
     */
    val user: LiveData<Resource<User>> = Transformations.switchMap(_userId) { userId ->
        if (userId == null) {
            AbsentLiveData.create()
        } else {
            userRepository.getUser(userId)
        }
    }

    // endregion Properties

    // region Methods

    /**
     * Sets the user id and triggers the switchMap backing the [user] property.
     */
    fun setUserId(userId: Int?) {
        if (_userId.value != userId) {
            _userId.value = userId
        }
    }

    /**
     * Re-sets user id to itself, which will also reset the switchMap backing the [user] property
     * in the case that an error was encountered during the previous attempt (i.e. bad network
     * connection, user was unauthorized, etc.)
     */
    fun retry() {
        _userId.value?.let {
            _userId.value = it
        }
    }

    // endregion Methods
}

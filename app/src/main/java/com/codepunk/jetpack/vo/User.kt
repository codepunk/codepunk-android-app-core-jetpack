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

package com.codepunk.jetpack.vo

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class representing a user.
 */
@Entity
data class User(

    /**
     * The unique id of the user.
     */
    @PrimaryKey
    val id: Int,

    /**
     * The user's name.
     */
    val name: String,

    /**
     * The user's email.
     */
    val email: String
)

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

// region Constants

// Names

/**
 * The "Accept" API header name.
 */
const val ACCEPT = "Accept"

/**
 * The "Authorization" API header name.
 */
const val AUTHORIZATION = "Authorization"

/**
 * The "Content-Type" API header name.
 */
const val CONTENT_TYPE = "Content-Type"

// Values

/**
 * A placeholder for an access token in endpoints that require authentication.
 */
const val ACCESS_TOKEN_PLACEHOLDER = "\$accessToken"

/**
 * The "application/json" header value.
 */
const val APPLICATION_JSON = "application/json"

/**
 * A value for the Authorization header, with a placeholder for access token that will
 * be resolved at runtime.
 */
const val BEARER = "Bearer $ACCESS_TOKEN_PLACEHOLDER"

// Name/value pairs

/**
 * A name/value pair for accepting application/json responses.
 */
const val ACCEPT_APPLICATION_JSON = "$ACCEPT: $APPLICATION_JSON"

/**
 * A name/value pair for bearer authorization header.
 */
const val AUTHORIZATION_BEARER = "$AUTHORIZATION: $BEARER"

/**
 * A name/value pair for specifying application/json Content-Type.
 */
const val CONTENT_TYPE_APPLICATION_JSON = "$CONTENT_TYPE: $APPLICATION_JSON"

// endregion Constants

package com.codepunk.jetpack.api

// region Constants

// Names

const val ACCEPT = "Accept"
const val AUTHORIZATION = "Authorization"
const val CONTENT_TYPE = "Content-Type"

// Values

const val ACCESS_TOKEN_PLACEHOLDER = "\$accessToken"
const val APPLICATION_JSON = "application/json"
const val BEARER = "Bearer $ACCESS_TOKEN_PLACEHOLDER"

// Name/value pairs

const val ACCEPT_APPLICATION_JSON = "$ACCEPT: $APPLICATION_JSON"
const val AUTHORIZATION_BEARER = "$AUTHORIZATION: $BEARER"
const val CONTENT_TYPE_APPLICATION_JSON = "$CONTENT_TYPE: $APPLICATION_JSON"

// endregion Constants

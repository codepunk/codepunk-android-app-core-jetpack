package com.codepunk.jetpack.util

import com.codepunk.jetpack.api.ACCESS_TOKEN_PLACEHOLDER
import com.codepunk.jetpack.api.AUTHORIZATION
import okhttp3.Interceptor
import okhttp3.Response

object AuthorizationInterceptor : Interceptor {

    var accessToken: String = ""

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(
            request.header(AUTHORIZATION)?.let { value ->
                request.newBuilder()
                    .header(AUTHORIZATION, value.replace(ACCESS_TOKEN_PLACEHOLDER, accessToken))
                    .build()
            } ?: request
        )
    }
}
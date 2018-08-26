package com.codepunk.jetpack.api

import androidx.lifecycle.LiveData
import com.codepunk.jetpack.vo.User
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * REST API access points
 */
interface UserWebservice {

    @GET("api/user")
    @Headers(ACCEPT_APPLICATION_JSON, AUTHORIZATION_BEARER)
    fun getUser(): LiveData<ApiResponse<User>>

}
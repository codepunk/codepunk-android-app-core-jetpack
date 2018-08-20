package com.codepunk.jetpack.api

import androidx.lifecycle.LiveData
import com.codepunk.jetpack.vo.User
import retrofit2.http.GET

/**
 * REST API access points
 */
interface UserWebservice {

    @GET("api/user")
    fun getUser(): LiveData<ApiResponse<User>>

}
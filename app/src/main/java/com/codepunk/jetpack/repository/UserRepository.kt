package com.codepunk.jetpack.repository

import android.util.Log
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

    fun getUser(id: Int): LiveData<Resource<User>> {
        return object : NetworkBoundResource<User, User>(appExecutors) {
            override fun saveCallResult(item: User) {
                userDao.insert(item)
            }

            override fun shouldFetch(data: User?) = data == null

            override fun loadFromDb() = userDao.getUser(id)

            override fun createCall(): LiveData<ApiResponse<User>> {
                // TODO TEMP
                Log.d(this@UserRepository::class.java.simpleName, "createCall: Here!")
                return userWebservice.getUser()
            }
        }.asLiveData()
    }

    // endregion Methods

}
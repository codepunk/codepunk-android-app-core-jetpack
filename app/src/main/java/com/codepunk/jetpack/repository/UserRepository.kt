package com.codepunk.jetpack.repository

import androidx.lifecycle.LiveData
import com.codepunk.jetpack.AppExecutors
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

    fun getUser(): LiveData<Resource<User>> {
        return object : NetworkBoundResource<User, User>(appExecutors) {
            override fun saveCallResult(item: User) {
                userDao.insert(item)
            }

            override fun shouldFetch(data: User?) = data == null

            override fun loadFromDb() = userDao.getUser(1) // TODO TEMP

            override fun createCall() = userWebservice.getUser()
        }.asLiveData()
    }

    // endregion Methods

}
package com.codepunk.jetpack.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codepunk.jetpack.vo.User

/**
 * Interface for database access for User related operations.
 */
@Dao
interface UserDao {

    // region Methods

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUser(id: Int): LiveData<User>

    // endregion Methods
}
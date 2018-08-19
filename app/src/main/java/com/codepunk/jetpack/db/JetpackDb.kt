package com.codepunk.jetpack.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codepunk.jetpack.vo.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class JetpackDb : RoomDatabase() {

    // region Methods

    abstract fun userDao(): UserDao

    // endregion Methods

}
/*
 * Copyright (C) 2017 The Android Open Source Project
 * Modifications Copyright (C) 2018 Codepunk, LLC
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
 *
 *
 * The original work can be found at The Android Open Source Project at
 *
 *      https://github.com/googlesamples
 *
 *      Repository:
 *      android-architecture-components
 *
 *      File:
 *      GithubBrowserSample/app/src/main/java/com/android/example/github/di/AppModule.kt
 *
 * Modifications:
 * August 2018: Jetpack-specific dependency creation; code organization and comments
 */

package com.codepunk.jetpack.di

import android.app.Application
import androidx.room.Room
import com.codepunk.jetpack.api.UserWebservice
import com.codepunk.jetpack.db.JetpackDb
import com.codepunk.jetpack.db.UserDao
import com.codepunk.jetpack.util.AuthorizationInterceptor
import com.codepunk.jetpack.util.LiveDataCallAdapterFactory
import com.codepunk.jetpack.util.generateSSLSocketFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Module responsible for providing application-level dependencies.
 */
@Module(includes = [ViewModelModule::class])
class AppModule {

    /**
     * Creates a [UserWebservice] instance.
     */
    @Singleton
    @Provides
    fun providesUserWebservice(): UserWebservice {
        return Retrofit.Builder()
            .client(OkHttpClient.Builder()
                .apply {
                    generateSSLSocketFactory()
                }
                .addInterceptor(AuthorizationInterceptor)
                .build())
            .baseUrl("https://codepunk.test")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(UserWebservice::class.java)
    }

    /**
     * Creates a [JetpackDb] instance.
     */
    @Singleton
    @Provides
    fun provideDb(app: Application): JetpackDb {
        return Room
            .databaseBuilder(app, JetpackDb::class.java, "jetpack.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    /**
     * Creates a [UserDao] instance.
     */
    @Singleton
    @Provides
    fun provideUserDao(db: JetpackDb): UserDao {
        return db.userDao()
    }
}
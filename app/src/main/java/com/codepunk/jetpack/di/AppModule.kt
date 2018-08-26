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

@Module(includes = [ViewModelModule::class])
class AppModule {
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

    @Singleton
    @Provides
    fun provideDb(app: Application): JetpackDb {
        return Room
            .databaseBuilder(app, JetpackDb::class.java, "jetpack.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: JetpackDb): UserDao {
        return db.userDao()
    }
}
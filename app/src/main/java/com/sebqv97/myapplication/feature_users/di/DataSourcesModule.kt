package com.sebqv97.myapplication.feature_users.di

import android.app.Application
import androidx.room.Room
import com.sebqv97.myapplication.feature_users.data.local.UsersDao
import com.sebqv97.myapplication.feature_users.data.local.UsersDatabase
import com.sebqv97.myapplication.feature_users.data.remote.UsersApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataSourcesModule {

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): UsersApi = retrofit.create(UsersApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(application: Application):UsersDatabase =
        Room.databaseBuilder(
        application,UsersDatabase::class.java,"users_db"
    ).build()

    @Provides
    @Singleton
    fun provideDatabaseDao(database: UsersDatabase):UsersDao = database.dao

}
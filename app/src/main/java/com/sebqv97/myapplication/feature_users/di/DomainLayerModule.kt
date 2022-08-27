package com.sebqv97.myapplication.feature_users.di

import androidx.compose.ui.input.key.Key.Companion.U
import com.sebqv97.myapplication.feature_users.data.local.UsersDao
import com.sebqv97.myapplication.feature_users.data.remote.UsersApi
import com.sebqv97.myapplication.feature_users.data.repository.UsersRepositoryImpl
import com.sebqv97.myapplication.feature_users.domain.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainLayerModule {

    @Provides
    fun provideUserRepository( usersDao: UsersDao, usersApi: UsersApi):UsersRepository = UsersRepositoryImpl(usersApi,usersDao)
}
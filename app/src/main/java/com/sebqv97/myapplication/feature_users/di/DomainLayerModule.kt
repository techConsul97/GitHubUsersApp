package com.sebqv97.myapplication.feature_users.di

import androidx.compose.ui.input.key.Key.Companion.U
import com.sebqv97.myapplication.feature_users.data.local.UsersDao
import com.sebqv97.myapplication.feature_users.data.remote.UsersApi
import com.sebqv97.myapplication.feature_users.data.repository.UsersRepositoryImpl
import com.sebqv97.myapplication.feature_users.domain.repository.UsersRepository
import com.sebqv97.myapplication.feature_users.domain.use_case.FavoriteUserUseCase
import com.sebqv97.myapplication.feature_users.domain.use_case.FetchUserUseCase
import com.sebqv97.myapplication.feature_users.domain.use_case.GetUsersDetailsUseCase
import com.sebqv97.myapplication.feature_users.domain.use_case.GetUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainLayerModule {

    @Provides
    fun provideUserRepository( usersDao: UsersDao, usersApi: UsersApi):UsersRepository = UsersRepositoryImpl(usersApi,usersDao)

    @Provides
    fun provideUserDetailsUseCase(repository: UsersRepository):GetUsersDetailsUseCase = GetUsersDetailsUseCase(repository)

    @Provides
    fun provideFavoriteUserUerCase(repository: UsersRepository):FavoriteUserUseCase = FavoriteUserUseCase(repository)

    @Provides
    fun provideSearchUserUseCase(repository: UsersRepository):FetchUserUseCase = FetchUserUseCase(repository)
}
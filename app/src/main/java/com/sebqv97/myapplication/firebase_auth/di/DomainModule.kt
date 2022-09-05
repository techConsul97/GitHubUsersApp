package com.sebqv97.myapplication.firebase_auth.di

import com.google.firebase.auth.FirebaseAuth
import com.sebqv97.myapplication.firebase_auth.data.repository.AuthRepositoryImpl
import com.sebqv97.myapplication.firebase_auth.domain.repository.AuthRepository
import com.sebqv97.myapplication.firebase_auth.domain.use_case.AuthorizeUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {


    @Provides
    fun providesAuthUseCase(repository: AuthRepository) = AuthorizeUserUseCase(repository)

    @Provides
    fun providesAuthRepository(
        firebaseAuth: FirebaseAuth):AuthRepository = AuthRepositoryImpl(firebaseAuth)


}
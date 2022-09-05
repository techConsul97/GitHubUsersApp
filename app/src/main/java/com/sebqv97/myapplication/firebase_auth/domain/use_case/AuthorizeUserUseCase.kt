package com.sebqv97.myapplication.firebase_auth.domain.use_case

import com.sebqv97.myapplication.firebase_auth.data.model.AuthUser
import com.sebqv97.myapplication.firebase_auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthorizeUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun createUser(authUser: AuthUser) = authRepository.createUser(authUser)

    suspend fun loginUser(authUser: AuthUser) = authRepository.loginUser(authUser)

}
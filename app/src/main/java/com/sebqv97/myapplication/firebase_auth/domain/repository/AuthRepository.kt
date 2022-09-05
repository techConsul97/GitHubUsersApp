package com.sebqv97.myapplication.firebase_auth.domain.repository

import com.sebqv97.myapplication.core.util.ResultState
import com.sebqv97.myapplication.firebase_auth.data.model.AuthUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun createUser(auth: AuthUser): Flow<ResultState<String>>

    suspend fun loginUser(auth: AuthUser): Flow<ResultState<String>>


}
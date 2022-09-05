package com.sebqv97.myapplication.firebase_auth.presentation

import com.sebqv97.myapplication.core.util.ErrorTypes
import com.sebqv97.myapplication.firebase_auth.data.model.AuthUser

data class AuthUserState(
    val user: AuthUser? = null,
    val encounteredError: ErrorTypes? = null,
    val isLoading: Boolean = false
)

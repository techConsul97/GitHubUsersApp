package com.sebqv97.myapplication.feature_users.presentation.user_details

import com.sebqv97.myapplication.core.util.ErrorTypes
import com.sebqv97.myapplication.feature_users.domain.model.UserDetailsItemModel

data class UserDetailsState(
    val isLoading: Boolean = false,
    val user: UserDetailsItemModel?= null,
    val error: ErrorTypes? = null
)
package com.sebqv97.myapplication.feature_users.presentation.user_list

import com.sebqv97.myapplication.core.util.ErrorTypes
import com.sebqv97.myapplication.feature_users.domain.model.UserItemModel

data class UsersListState(
    val isLoading: Boolean = false,
    val users: List<UserItemModel> = emptyList(),
    val fetchedUser:UserItemModel? = null,
    val error: ErrorTypes? = null
)
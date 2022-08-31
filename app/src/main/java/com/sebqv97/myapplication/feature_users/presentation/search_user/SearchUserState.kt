package com.sebqv97.myapplication.feature_users.presentation.search_user

import com.sebqv97.myapplication.core.util.ErrorTypes
import com.sebqv97.myapplication.feature_users.domain.model.SearchUsersItemModel
import com.sebqv97.myapplication.feature_users.domain.model.SearchUsersModel
import com.sebqv97.myapplication.feature_users.domain.model.UserItemModel


data class SearchUserState(
    val isLoading: Boolean = false,
    val totalUsersFound:Int = 0,
    val foundUsers: List<SearchUsersItemModel> = emptyList(),
    val error: ErrorTypes? = null
)

enum class SearchUserWidgetState {
    OPENED,
    CLOSED
}
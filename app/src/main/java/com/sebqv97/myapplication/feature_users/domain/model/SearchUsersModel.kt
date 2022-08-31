package com.sebqv97.myapplication.feature_users.domain.model

import com.google.gson.annotations.SerializedName
import com.sebqv97.myapplication.feature_users.data.remote.dto.SearchUsersItemDto


data class SearchUsersModel(

    val users: List<SearchUsersItemModel>,
    val totalUsersCount: Int
)
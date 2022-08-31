package com.sebqv97.myapplication.feature_users.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.sebqv97.myapplication.feature_users.domain.model.SearchUsersModel

data class SearchUsersDto(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<SearchUsersItemDto>,
    @SerializedName("total_count")
    val totalCount: Int
){
    fun toSearchUsersModel():SearchUsersModel= SearchUsersModel(
        users = items.map { it.toSearchUsersItemModel() },
        totalUsersCount = totalCount
    )
}
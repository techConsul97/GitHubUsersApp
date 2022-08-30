package com.sebqv97.myapplication.feature_users.domain.model

import com.sebqv97.myapplication.feature_users.data.local.entity.UserEntity

data class UserItemModel(

    val avatarUrl: String?,
    val id: Int?,
    val username: String?,
    var isFavorite: Boolean = false

){
    fun toUserEntity():UserEntity = UserEntity(
        avatarUrl = avatarUrl!!,
        id = id!!,
        username = username!!,
        isFavorite = false
    )

    fun updateFavoriteStatus(newValue: Boolean){
        isFavorite = newValue
    }

}
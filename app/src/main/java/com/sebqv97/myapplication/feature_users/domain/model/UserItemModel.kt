package com.sebqv97.myapplication.feature_users.domain.model

import com.sebqv97.myapplication.feature_users.data.local.entity.UserEntity

data class UserItemModel(

    val avatarUrl: String,
    val followersUrl: String,
    val followingUrl: String,
    val htmlUrl: String,
    val id: Int,
    val username: String,
    val reposUrl: String,
){

}
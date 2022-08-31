package com.sebqv97.myapplication.feature_users.domain.model

import com.google.gson.annotations.SerializedName

data class SearchUsersItemModel(


    val avatarUrl: String,
    val followersUrl: String,
    val followingUrl: String,
    val htmlUrl: String,
    val id: Int,
    val username: String,
    val reposUrl: String,
    val score: Int,
    val siteAdmin: Boolean,
    val starredUrl: String,
    val subscriptionsUrl: String,
    val type: String,
    val url: String
)
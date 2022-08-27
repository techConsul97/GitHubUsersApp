package com.sebqv97.myapplication.feature_users.domain.model

import com.google.gson.annotations.SerializedName


data class UserDetailsItemModel(
    val avatarUrl: String?,
    val bio: String?,
    val blog: String?,
    val createdAt: String?,
    val email: String?,
    val followers: Int?,
    val following: Int?,
    val followingUrl: String?,
    val hireable: Boolean?,
    val id: Int?,
    val username: String?,
    val name: String?,
    val reposUrl: String?,

    val twitterUsername: String?,

)
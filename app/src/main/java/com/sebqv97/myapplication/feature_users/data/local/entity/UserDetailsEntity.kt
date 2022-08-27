package com.sebqv97.myapplication.feature_users.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sebqv97.myapplication.feature_users.domain.model.UserDetailsItemModel

@Entity(tableName = "user_details")
data class UserDetailsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val avatarUrl: String?,
    val bio: String?,
    val blog: String?,
    val createdAt: String?,
    val email: String?,
    val followers: Int?,
    val following: Int?,
    val followingUrl: String?,
    val hireable: Boolean?,

    val username: String?,
    val name: String?,
    val reposUrl: String?,
    val twitterUsername: String?,

    ){
    fun toUserDetailsItemModel() = UserDetailsItemModel(
        avatarUrl = avatarUrl,
        bio = bio,
        blog = blog,
        createdAt = createdAt,
        email = email,
        followers = followers,
        following = following,
        followingUrl = followingUrl,
        hireable = hireable,
        id = id,
        username = username,
        name = name,
        reposUrl = reposUrl,
        twitterUsername = twitterUsername
    )
}
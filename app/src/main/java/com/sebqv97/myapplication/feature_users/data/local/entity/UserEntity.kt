package com.sebqv97.myapplication.feature_users.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sebqv97.myapplication.feature_users.domain.model.UserItemModel

@Entity(tableName = "users")
data class UserEntity (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val avatarUrl: String,
    val followersUrl: String,
    val followingUrl: String,
    val htmlUrl: String,
    val username: String,
    val reposUrl: String,
    ){
    fun toUserItemModel(): UserItemModel = UserItemModel(

        avatarUrl = avatarUrl,
        htmlUrl = htmlUrl,
        followersUrl = followersUrl,
        followingUrl = followingUrl,
        reposUrl = reposUrl,
        username = username,
        id = id

    )
}


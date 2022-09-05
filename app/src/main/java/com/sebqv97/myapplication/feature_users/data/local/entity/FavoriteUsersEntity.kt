package com.sebqv97.myapplication.feature_users.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sebqv97.myapplication.feature_users.domain.model.UserItemModel

@Entity(tableName = "favorites_users")
data class FavoriteUsersEntity(
    @PrimaryKey(autoGenerate = false)
    val username: String,
    val avatarUrl: String?,
    val id: Int?
)
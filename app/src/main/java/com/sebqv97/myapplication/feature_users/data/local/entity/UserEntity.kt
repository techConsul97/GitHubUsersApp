package com.sebqv97.myapplication.feature_users.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sebqv97.myapplication.feature_users.domain.model.UserItemModel

@Entity(tableName = "users")
data class UserEntity (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val avatarUrl: String,
    val username: String,

    ){
    fun toUserItemModel(): UserItemModel = UserItemModel(

        avatarUrl = avatarUrl,
        username = username,
        id = id

    )
}


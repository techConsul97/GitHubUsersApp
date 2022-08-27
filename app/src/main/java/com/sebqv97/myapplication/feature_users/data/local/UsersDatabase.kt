package com.sebqv97.myapplication.feature_users.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sebqv97.myapplication.feature_users.data.local.entity.UserDetailsEntity
import com.sebqv97.myapplication.feature_users.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class,UserDetailsEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class UsersDatabase : RoomDatabase() {
    abstract val dao: UsersDao
}
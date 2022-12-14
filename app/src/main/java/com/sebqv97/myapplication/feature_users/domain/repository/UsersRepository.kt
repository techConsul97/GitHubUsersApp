package com.sebqv97.myapplication.feature_users.domain.repository

import com.sebqv97.myapplication.feature_users.data.local.entity.UserDetailsEntity
import com.sebqv97.myapplication.feature_users.data.local.entity.UserEntity
import com.sebqv97.myapplication.feature_users.data.remote.dto.ListUsersDto
import com.sebqv97.myapplication.feature_users.data.remote.dto.UserDetailsDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


interface UsersRepository {

    fun readUsers(): Flow<List<UserEntity>>
    fun readUser(searchedUsername: String):Flow<UserEntity>
    fun readSpecificUser(searchedUsername: String):Flow<UserDetailsEntity>

    suspend fun insertUser(user:UserDetailsEntity)
    suspend fun insertUsers(users:List<UserEntity>)

    suspend fun getUsers():List<ListUsersDto>
    suspend fun getUser(searchedUsername:String): Response<UserDetailsDto>


    suspend fun deleteUser(user:UserDetailsEntity)
    suspend fun deleteUsers()





}
package com.sebqv97.myapplication.feature_users.data.repository

import android.util.Log
import com.sebqv97.myapplication.feature_users.data.local.UsersDao
import com.sebqv97.myapplication.feature_users.data.local.entity.UserDetailsEntity
import com.sebqv97.myapplication.feature_users.data.local.entity.UserEntity
import com.sebqv97.myapplication.feature_users.data.remote.UsersApi
import com.sebqv97.myapplication.feature_users.data.remote.dto.ListUsersDto
import com.sebqv97.myapplication.feature_users.data.remote.dto.UserDetailsDto
import com.sebqv97.myapplication.feature_users.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val usersApi: UsersApi,
    private val usersDao: UsersDao
) : UsersRepository {
    override suspend fun getUsers(): List<ListUsersDto> {
        return usersApi.getAllData()
    }

    override suspend fun getUser(searchedUsername: String): Response<UserDetailsDto> {
        return  usersApi.getDataFromPath(searchedUsername)


    }

    override suspend fun deleteUsers() {
        usersDao.deleteAll()
    }

    override suspend fun deleteUser(user:UserDetailsEntity) {
        usersDao.deleteOne(user)

    }

    override fun readSpecificUser(searchedUsername: String): Flow<UserDetailsEntity> {
       return usersDao.getSpecificUser(searchedUsername)
    }

    override suspend fun insertUser(user: UserDetailsEntity) {
        usersDao.insertOne(user)
    }

    override suspend fun insertUsers(users: List<UserEntity>) {
        usersDao.insertAll(users)
    }

    override fun readUsers(): Flow<List<UserEntity>> {
        return usersDao.getAll()
    }

    override fun readUser(searchedUsername: String): Flow<UserEntity>{
        return usersDao.getUser(searchedUsername)
    }
}
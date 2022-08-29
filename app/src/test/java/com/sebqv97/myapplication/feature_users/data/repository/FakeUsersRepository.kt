package com.sebqv97.myapplication.feature_users.data.repository

import com.sebqv97.myapplication.feature_users.data.local.entity.UserDetailsEntity
import com.sebqv97.myapplication.feature_users.data.local.entity.UserEntity
import com.sebqv97.myapplication.feature_users.data.remote.dto.ListUsersDto
import com.sebqv97.myapplication.feature_users.data.remote.dto.UserDetailsDto
import com.sebqv97.myapplication.feature_users.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class FakeUsersRepository : UsersRepository {

    private val _usersTable = mutableListOf<UserEntity>()
    private val _userDetailsTable = mutableListOf<UserDetailsEntity>()

    override fun readUsers(): Flow<List<UserEntity>>  = flow {
        emit(_usersTable)
    }



    override fun readUser(searchedUsername: String): Flow<UserEntity> = flow {
        //emit(_userDetailsTable.first{it.username ==searchedUsername}
    }


    override fun readSpecificUser(searchedUsername: String): Flow<UserDetailsEntity> = flow {
        emit(_userDetailsTable.first{it.username ==searchedUsername})
    }

    override suspend fun insertUser(user: UserDetailsEntity) {
       _userDetailsTable.add(user)
    }

    override suspend fun insertUsers(users: List<UserEntity>) {
        for(user in users){
            _usersTable.add(user)
        }
    }

    override suspend fun getUsers(): List<ListUsersDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(searchedUsername: String): Response<UserDetailsDto> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(user: UserDetailsEntity) {
        _userDetailsTable.remove(user)
    }

    override suspend fun deleteUsers() {
       _usersTable.clear()
    }
}
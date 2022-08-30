package com.sebqv97.myapplication.feature_users.data.local

import androidx.room.*
import com.sebqv97.myapplication.feature_users.data.local.entity.UserDetailsEntity
import com.sebqv97.myapplication.feature_users.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface UsersDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneUserList(entity:UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entitiesList:List<UserEntity>)


    @Query("DELETE FROM users")
    suspend fun deleteAll()

    @Query("SELECT * FROM users")
    fun getAll() : Flow<List<UserEntity>>

    @Query("SELECT * FROM users WHERE username LIKE :searchedUserName")
    fun getUser(searchedUserName:String):Flow<UserEntity>




    @Delete
    suspend fun deleteOneUserDetails(user:UserDetailsEntity)

    @Delete
    suspend fun deleteOneUserUserList(user:UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(user: UserDetailsEntity)

   @Query("SELECT * FROM USER_DETAILS WHERE username LIKE :searchedUser")
    fun getSpecificUser(searchedUser: String):Flow<UserDetailsEntity?>


}
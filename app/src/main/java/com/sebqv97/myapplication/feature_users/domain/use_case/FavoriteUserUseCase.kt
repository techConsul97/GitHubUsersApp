package com.sebqv97.myapplication.feature_users.domain.use_case

import com.sebqv97.myapplication.feature_users.domain.model.UserItemModel
import com.sebqv97.myapplication.feature_users.domain.repository.UsersRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class FavoriteUserUseCase @Inject constructor(
    val repository: UsersRepository
) {
  suspend fun insertFavoriteUser(user:UserItemModel){

       repository.insertFavoriteUser(user.toFavoriteUserEntity())
   }

   suspend fun deleteFavoriteUser(user:UserItemModel){
       repository.deleteFavoriteUser(user.toFavoriteUserEntity())

    }

    fun getFavoriteUsers() = repository.getFavoriteUsers()

    suspend fun checkUserIsInFavorite(username:String) = repository.getFavoriteUserByUsername(username)





}
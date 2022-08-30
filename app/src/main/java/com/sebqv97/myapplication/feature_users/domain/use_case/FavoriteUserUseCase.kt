package com.sebqv97.myapplication.feature_users.domain.use_case

import com.sebqv97.myapplication.feature_users.domain.model.UserItemModel
import com.sebqv97.myapplication.feature_users.domain.repository.UsersRepository
import javax.inject.Inject

class FavoriteUserUseCase @Inject constructor(
    val repository: UsersRepository
) {
    operator fun invoke(favoriteUser:UserItemModel){


    }
}
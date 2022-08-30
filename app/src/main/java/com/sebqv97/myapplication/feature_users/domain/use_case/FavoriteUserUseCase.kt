package com.sebqv97.myapplication.feature_users.domain.use_case

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.sebqv97.myapplication.core.util.ErrorTypes
import com.sebqv97.myapplication.core.util.Resource
import com.sebqv97.myapplication.feature_users.domain.model.UserItemModel
import com.sebqv97.myapplication.feature_users.domain.repository.UsersRepository
import com.sebqv97.myapplication.feature_users.presentation.user_list.UsersListState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoriteUserUseCase @Inject constructor(
    val repository: UsersRepository
) {



    operator fun invoke(favoriteUser:UserItemModel): Flow<Resource<UserItemModel>> = flow{

        //delete user from table as to not create bad data
        repository.deleteUserFromUserList(favoriteUser.toUserEntity())

        //set isFavorite Property to 'true'
        favoriteUser.updateFavoriteStatus(true)

        //insert into db with favorite flag set
        repository.insertUserFromUserList(favoriteUser.toUserEntity())

        //retrieve the element and return it to viewModel
        val retrievedUser = repository.readUser(favoriteUser.username!!).collect{ user ->
            user.let { emit(Resource.Success(it.toUserItemModel())) }
            emit(Resource.Error<UserItemModel>(ErrorTypes.DBInsertionSuccessRetrievingFailed()))
        }

    }
}
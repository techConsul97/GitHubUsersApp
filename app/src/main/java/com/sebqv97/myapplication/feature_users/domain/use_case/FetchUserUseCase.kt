package com.sebqv97.myapplication.feature_users.domain.use_case

import com.sebqv97.myapplication.core.util.ErrorTypes
import com.sebqv97.myapplication.core.util.ResultState
import com.sebqv97.myapplication.feature_users.domain.model.UserItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchUserUseCase @Inject constructor(
    private val getUsersDetailsUseCase: GetUsersDetailsUseCase
) {
    operator fun invoke(searchedUserByUserName: String?): Flow<ResultState<UserItemModel>> = flow {

        if (searchedUserByUserName.isNullOrEmpty()) {
            emit(ResultState.Error<UserItemModel>(ErrorTypes.EmptySearchField()))
        }
        //calling UserDetailsUseCase as to get the User with Details and convert it to a User from List
        getUsersDetailsUseCase(searchedUserByUserName!!).collect { status ->
            when (status) {
                is ResultState.Success -> {
                    val userItemModel = status.data.let { it!!.toUserItemModel() }
                    emit(ResultState.Success(userItemModel))
                }
                is ResultState.Error -> {
                    emit(ResultState.Error<UserItemModel>(status.errorType!!))
                }
                is ResultState.Loading -> {}


            }
        }
    }


}
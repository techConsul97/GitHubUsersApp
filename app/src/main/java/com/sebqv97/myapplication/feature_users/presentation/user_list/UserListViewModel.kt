package com.sebqv97.myapplication.feature_users.presentation.user_list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebqv97.myapplication.core.util.ResultState
import com.sebqv97.myapplication.feature_users.domain.model.UserItemModel
import com.sebqv97.myapplication.feature_users.domain.use_case.FavoriteUserUseCase
import com.sebqv97.myapplication.feature_users.domain.use_case.FetchUserUseCase
import com.sebqv97.myapplication.feature_users.domain.use_case.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val fetchUserUseCase: FetchUserUseCase,
    private val getUsersUseCase: GetUsersUseCase,
    private val favoriteUserUseCase: FavoriteUserUseCase

) : ViewModel() {

    private val _state = mutableStateOf(UsersListState())
    val state: State<UsersListState> = _state

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            getUsersUseCase().collect { result ->
                when (result) {
                    is ResultState.Success -> {
                        result.data!!.forEach {
                            if (checkUserIsFavorite(it.username!!)) {
                                it.isFavorite = true
                            }
                        }
                        _state.value = UsersListState(users = result.data)

                    }
                    is ResultState.Error -> {
                        _state.value = UsersListState(error = result.errorType)
                    }
                    is ResultState.Loading -> {
                        _state.value = UsersListState(isLoading = true)
                    }
                }
            }


        }
    }

    fun getUserByUsername(searchedUserByUsername: String?) {
        viewModelScope.launch {
            fetchUserUseCase(searchedUserByUsername).collect { result ->
                when (result) {
                    is ResultState.Success -> {
                        _state.value = UsersListState(users = listOf(result.data!!))
                        this.coroutineContext.cancel()
                        Log.d("state", _state.value.toString())

                    }
                    is ResultState.Error -> {
                        _state.value = UsersListState(error = result.errorType)
                        this.coroutineContext.cancel()
                    }
                    is ResultState.Loading -> {
                        _state.value = UsersListState(isLoading = true)
                        this.coroutineContext.cancel()
                    }
                }

            }
        }

    }

    fun markUserAsFavorite(user: UserItemModel) {
        val job = viewModelScope.launch {
            favoriteUserUseCase.insertFavoriteUser(user)
        }
        job.cancel()
        getUsers()

    }

    fun unmarkUserFromFavorite(user: UserItemModel) {
        viewModelScope.launch {
            favoriteUserUseCase.deleteFavoriteUser(user)
        }
    }

    fun checkUserIsFavorite(username: String): Boolean {
        var appears = false
        viewModelScope.launch {
            appears = favoriteUserUseCase.checkUserIsInFavorite(username).first() != null

        }
        return appears
    }


}
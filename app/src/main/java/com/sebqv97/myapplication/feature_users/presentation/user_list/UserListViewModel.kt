package com.sebqv97.myapplication.feature_users.presentation.user_list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebqv97.myapplication.core.util.ResultState
import com.sebqv97.myapplication.feature_users.domain.use_case.FetchUserUseCase
import com.sebqv97.myapplication.feature_users.domain.use_case.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val fetchUserUseCase: FetchUserUseCase,
    private val getUsersUseCase: GetUsersUseCase

) : ViewModel() {

    private val _state = mutableStateOf(UsersListState())
    val state: State<UsersListState> = _state

    init {
        getUsers()
    }

    private fun getUsers() {
        getUsersUseCase().onEach { result ->
            when (result) {
                is ResultState.Success -> {
                    _state.value = UsersListState(users = result.data!!)

                }
                is ResultState.Error -> {
                    _state.value = UsersListState(error = result.errorType)
                }
                is ResultState.Loading -> {
                    _state.value = UsersListState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }

    fun getUserByUsername(searchedUserByUsername: String?) {
        viewModelScope.launch {
            fetchUserUseCase(searchedUserByUsername).collect { result ->
                when (result) {
                    is ResultState.Success -> {
                        _state.value = UsersListState(users = listOf(result.data!!))
                        this.coroutineContext.cancel()
                        Log.d("state",_state.value.toString())

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
}
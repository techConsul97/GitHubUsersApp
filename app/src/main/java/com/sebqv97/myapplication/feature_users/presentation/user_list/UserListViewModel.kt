package com.sebqv97.myapplication.feature_users.presentation.user_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebqv97.myapplication.core.util.Resource
import com.sebqv97.myapplication.feature_users.domain.model.UserItemModel
import com.sebqv97.myapplication.feature_users.domain.use_case.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel(){
    private lateinit var usersList:List<UserItemModel>
    private val _state = mutableStateOf(UsersListState())
    val state: State<UsersListState> = _state

    init {
        getUsers()
    }

    private fun getUsers() {
        getUsersUseCase().onEach{result->
            when(result){
                is Resource.Success -> {
                    _state.value = UsersListState(users = result.data!!)
                    usersList = result.data
                }
                is Resource.Error ->{
                    _state.value = UsersListState(error = result.errorType)
                }
                is Resource.Loading -> {
                    _state.value = UsersListState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }


}
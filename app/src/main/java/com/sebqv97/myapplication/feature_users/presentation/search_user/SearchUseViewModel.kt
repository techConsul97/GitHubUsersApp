package com.sebqv97.myapplication.feature_users.presentation.search_user

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebqv97.myapplication.core.util.Resource
import com.sebqv97.myapplication.feature_users.domain.use_case.FetchUserUseCase
import com.sebqv97.myapplication.feature_users.presentation.Screens
import com.sebqv97.myapplication.feature_users.presentation.user_list.UsersListState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchUseViewModel @Inject constructor(
    private val fetchUserUseCase: FetchUserUseCase

):ViewModel() {



    private val _searchWidgetState:MutableState<SearchUserWidgetState> =
        mutableStateOf(value = SearchUserWidgetState.CLOSED)
    val searchWidgetState: State<SearchUserWidgetState>get()  = _searchWidgetState

    private val _searchUserTextState:MutableState<String> =
        mutableStateOf(value = "")
    val searchUserTextState get()= _searchUserTextState

    private val _foundUsersState = mutableStateOf(SearchUserState())
    val foundUsersState: State<SearchUserState> =_foundUsersState


    fun updateSearchWidgetState(newValue: SearchUserWidgetState){
        _searchWidgetState.value = newValue
    }

    fun updateSearchUserTextState(newTextValue: String){
        _searchUserTextState.value = newTextValue
    }





    fun getUserByUsername(searchedUserByUsername: String?) {
        viewModelScope.launch {
            fetchUserUseCase(searchedUserByUsername).onEach{ result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            _foundUsersState.value = SearchUserState(foundUsers = it.users, totalUsersFound = it.totalUsersCount)
                            Log.d("State: ",_foundUsersState.value.toString())
                        }

                    }
                    is Resource.Error -> {
                        result.errorType?.let {
                            _foundUsersState.value = SearchUserState(error = it)
                            Log.d("State: ",_foundUsersState.value.toString())
                        }


                    }
                    is Resource.Loading -> {
                        _foundUsersState.value = SearchUserState(isLoading = true)
                        Log.d("State: ",_foundUsersState.value.toString())

                    }
                }

            }.launchIn(viewModelScope)
        }

    }


}
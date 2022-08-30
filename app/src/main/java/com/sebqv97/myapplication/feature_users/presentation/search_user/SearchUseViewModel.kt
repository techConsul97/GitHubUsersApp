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
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchUseViewModel @Inject constructor(
    private val fetchUserUseCase: FetchUserUseCase

):ViewModel() {

    private val _currentScreen:MutableState<Screens> =
        mutableStateOf(value = Screens.UsersScreen)
    val currentScreen: State<Screens> get()= _currentScreen

    private val _searchWidgetState:MutableState<SearchUserWidgetState> =
        mutableStateOf(value = SearchUserWidgetState.CLOSED)
    val searchWidgetState: State<SearchUserWidgetState>get()  = _searchWidgetState

    private val _searchUserTextState:MutableState<String> =
        mutableStateOf(value = "")
    val searchUserTextState get()= _searchUserTextState


    fun updateSearchWidgetState(newValue: SearchUserWidgetState){
        _searchWidgetState.value = newValue
    }

    fun updateSearchUserTextState(newTextValue: String){
        _searchUserTextState.value = newTextValue
    }

    fun updateCurrentScreen(newValue: Screens){
        _currentScreen.value = newValue
    }

//    fun getUserByUsername(searchedUserByUsername: String?) {
//        viewModelScope.launch {
//            fetchUserUseCase(searchedUserByUsername).collect { result ->
//                when (result) {
//                    is Resource.Success -> {
//                        _state.value = UsersListState(users = listOf(result.data!!))
//                        this.coroutineContext.cancel()
//                        Log.d("state",_state.value.toString())
//
//                    }
//                    is Resource.Error -> {
//                        _state.value = UsersListState(error = result.errorType)
//                        this.coroutineContext.cancel()
//                    }
//                    is Resource.Loading -> {
//                        _state.value = UsersListState(isLoading = true)
//                        this.coroutineContext.cancel()
//                    }
//                }
//
//            }
//        }
//
//    }


}
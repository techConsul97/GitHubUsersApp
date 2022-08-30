package com.sebqv97.myapplication.feature_users.presentation.user_details

import android.provider.SyncStateContract
import android.view.View
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebqv97.myapplication.core.util.Resource
import com.sebqv97.myapplication.feature_users.common.Constants
import com.sebqv97.myapplication.feature_users.domain.use_case.GetUsersDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject



@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    val getUsersDetailsUseCase: GetUsersDetailsUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(UserDetailsState())
    val state: State<UserDetailsState> = _state


        init {
            savedStateHandle.get<String>(Constants.PARAM_USERNAME)?.let { username ->
                getUserDetails(username)
            }
        }



    private fun getUserDetails(searchedUser:String){

        getUsersDetailsUseCase(searchedUser).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = UserDetailsState(user = result.data)
                }
                is Resource.Error->{
                    _state.value = UserDetailsState(error = result.errorType)
                }
                is Resource.Loading ->{
                    _state.value = UserDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
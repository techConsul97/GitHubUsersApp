package com.sebqv97.myapplication.feature_users.presentation.add_favorite

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebqv97.myapplication.core.util.Resource
import com.sebqv97.myapplication.feature_users.domain.model.UserItemModel
import com.sebqv97.myapplication.feature_users.domain.use_case.FavoriteUserUseCase
import com.sebqv97.myapplication.feature_users.presentation.user_list.UsersListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddFavoriteUserViewModel @Inject constructor(
    private val favoriteUserUseCase: FavoriteUserUseCase
): ViewModel() {

    private val _state = mutableStateOf(UserItemModel(null,null,null))
    val state: State<UserItemModel> = _state


    fun updateUserState(newValue:UserItemModel){
        _state.value = newValue
    }
    private fun markFavoriteUser(user:UserItemModel){


        viewModelScope.launch {
           val result = favoriteUserUseCase(user).firstOrNull()
            when(result){
                is  Resource.Success -> {

                }
            }
        }
    }
}
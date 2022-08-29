package com.sebqv97.myapplication.feature_users.presentation.search_user

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sebqv97.myapplication.core.util.Resource
import com.sebqv97.myapplication.feature_users.domain.use_case.FetchUserUseCase
import com.sebqv97.myapplication.feature_users.presentation.user_list.UsersListState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class SearchUseViewModel @Inject constructor(


):ViewModel() {
    private val _searchWidgetState:MutableState<SearchUserWidgetState> =
        mutableStateOf(value = SearchUserWidgetState.CLOSED)
    val searchWidgetState: State<SearchUserWidgetState> = _searchWidgetState

    private val _searchUserTextState:MutableState<String> =
        mutableStateOf(value = "")
    val searchUserTextState= _searchUserTextState


    fun updateSearchWidgetState(newValue: SearchUserWidgetState){
        _searchWidgetState.value = newValue
    }

    fun updateSearchUserTextState(newTextValue: String){
        _searchUserTextState.value = newTextValue
    }

}
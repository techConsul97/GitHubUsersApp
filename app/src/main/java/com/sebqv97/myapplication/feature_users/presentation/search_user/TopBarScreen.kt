package com.sebqv97.myapplication.feature_users.presentation

import android.transition.Scene
import android.util.Log
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.sebqv97.myapplication.feature_users.presentation.search_user.SearchUseViewModel
import com.sebqv97.myapplication.feature_users.presentation.search_user.SearchUserWidgetState
import com.sebqv97.myapplication.feature_users.presentation.user_list.UserListViewModel
import com.sebqv97.myapplication.feature_users.presentation.user_list.components.SearchUserAppBar


@Composable
fun TopBarScreen(searchUseViewModel: SearchUseViewModel, userListViewModel: UserListViewModel){

    val searchWidgetState by searchUseViewModel.searchWidgetState
    val searchUserTextState by searchUseViewModel.searchUserTextState

        TopAppBar(
            searchWidgetState = searchWidgetState,
            searchTextState = searchUserTextState,
            onTextChange = {
                           searchUseViewModel.updateSearchUserTextState(newTextValue = it)
            },
            onCloseClicked = {
                            searchUseViewModel.updateSearchWidgetState(newValue = SearchUserWidgetState.CLOSED)

            },
            onSearchClicked = {
                Log.d("Searched Username: ", it)
                userListViewModel.getUserByUsername(it)

            },
            onSearchTriggered = {
                searchUseViewModel.updateSearchWidgetState(newValue = SearchUserWidgetState.OPENED)
            }
        )

}

@Composable
fun DefaultAppBar(onSearchClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "Home"
            )
        },
        actions = {
            IconButton(
                onClick = { onSearchClicked() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Black
                )
            }
        },
        backgroundColor = Color.Transparent
    )
}

@Composable
fun TopAppBar(
    searchWidgetState: SearchUserWidgetState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit
) {
    when (searchWidgetState) {
        SearchUserWidgetState.CLOSED -> {

            DefaultAppBar(
                onSearchClicked = onSearchTriggered
            )
        }
        SearchUserWidgetState.OPENED -> {
            SearchUserAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClick = onCloseClicked,
                onSearchClick = onSearchClicked,
                modifier = Modifier
            )
        }
    }
}

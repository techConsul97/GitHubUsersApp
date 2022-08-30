package com.sebqv97.myapplication.feature_users.presentation

import android.transition.Scene
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sebqv97.myapplication.feature_users.presentation.search_user.SearchUseViewModel
import com.sebqv97.myapplication.feature_users.presentation.search_user.SearchUserWidgetState
import com.sebqv97.myapplication.feature_users.presentation.user_list.UserListViewModel
import com.sebqv97.myapplication.feature_users.presentation.user_list.components.SearchUserAppBar


@Composable
fun TopBarScreen(searchUseViewModel: SearchUseViewModel = hiltViewModel()){

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
              //  userListViewModel.getUserByUsername(it)

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
    onSearchTriggered: () -> Unit,
    viewModel: SearchUseViewModel = hiltViewModel()
) {
    val currentScreen by viewModel.currentScreen

    when (searchWidgetState) {
        SearchUserWidgetState.CLOSED -> {

            if (currentScreen == Screens.UsersScreen) {
                Box(modifier = Modifier.background(Color.Transparent, shape = RectangleShape)) {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        IconButton(onClick = { onSearchTriggered() }) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Trigger Search"
                            )

                        }
                    }
                }
            }

//
//            DefaultAppBar(
//                onSearchClicked = onSearchTriggered
//            )
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


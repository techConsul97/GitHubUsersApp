package com.sebqv97.myapplication.feature_users.presentation.user_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sebqv97.myapplication.feature_users.presentation.Screens
import com.sebqv97.myapplication.feature_users.presentation.TopBarScreen
import com.sebqv97.myapplication.feature_users.presentation.search_user.SearchUseViewModel
import com.sebqv97.myapplication.feature_users.presentation.search_user.SearchUserWidgetState
import com.sebqv97.myapplication.feature_users.presentation.user_list.UserListViewModel
import com.sebqv97.myapplication.feature_users.utils.getWordsUseCaseErrorHandler
import kotlinx.coroutines.delay


@Composable
fun UsersScreen(
    navController: NavController,
    viewModel: UserListViewModel = hiltViewModel(),
    searchBarViewModel: SearchUseViewModel = hiltViewModel(),
    modifier: Modifier
) {
    val state = viewModel.state.value
    var currentScreen = remember{searchBarViewModel.currentScreen.value}


    Column() {
        TopBarScreen(modifier = modifier.padding(bottom = 40.dp))
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            LazyColumn(
                modifier = modifier.fillMaxSize()
            ) {
                items(state.users) { user ->
                    UserLayout(
                        user = user,
                        onUserClicked = {
                            searchBarViewModel.updateCurrentScreen(Screens.UserDetailScreen)
                            currentScreen = Screens.UserDetailScreen
                            searchBarViewModel.updateSearchWidgetState(SearchUserWidgetState.CLOSED)
                            navController.navigate(Screens.UserDetailScreen.route + "/${user.username}")
                        },
                        onFavoriteClicked = {},//ToBeImplemented
                        modifier = modifier
                    )
                }
            }
            if (state.error != null) {
                val errorMessage = getWordsUseCaseErrorHandler(state.error)
                Text(
                    text = errorMessage,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.error,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )

            }
            if (state.isLoading) {
                CircularProgressIndicator(modifier = modifier.align(Alignment.Center))

            }


        }
    }



}

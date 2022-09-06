package com.sebqv97.myapplication.feature_bottom_drawer

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sebqv97.myapplication.feature_users.presentation.favorite_users.FavoriteUsersScreen
import com.sebqv97.myapplication.feature_users.presentation.search_user.SearchUserScreen
import com.sebqv97.myapplication.firebase_auth.presentation.AuthUserScreen


@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ""
    ) {
        composable(route = BottomBarScreen.FavoriteUsers.route) {
            FavoriteUsersScreen()
        }
        composable(route = BottomBarScreen.Profile.route) {
            AuthUserScreen()
        }
        composable(route = BottomBarScreen.SearchUser.route) {

            SearchUserScreen()
        }
    }
}


package com.sebqv97.myapplication.feature_users.presentation

sealed class Screens(val route: String) {

    object UsersScreen: Screens("users_screen")
    object UserDetailScreen: Screens("user_detail_screen")
    object SearchUsersScreen :Screens("search_users")


}
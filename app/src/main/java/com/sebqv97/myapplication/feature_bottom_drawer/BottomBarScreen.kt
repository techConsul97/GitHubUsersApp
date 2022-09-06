package com.sebqv97.myapplication.feature_bottom_drawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object SearchUser : BottomBarScreen(
        route = "users",
        title = "Search User",
        icon = Icons.Default.Search
    )

    object Profile : BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )

    object FavoriteUsers : BottomBarScreen(
        route = "settings",
        title = "Settings",
        icon = Icons.Default.Favorite
    )
}
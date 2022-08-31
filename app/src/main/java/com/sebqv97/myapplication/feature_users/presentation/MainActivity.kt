package com.sebqv97.myapplication.feature_users.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sebqv97.myapplication.feature_users.presentation.search_user.SearchUseViewModel
import com.sebqv97.myapplication.feature_users.presentation.search_user.SearchUsersScreen
import com.sebqv97.myapplication.feature_users.presentation.search_user.TopBarScreen
import com.sebqv97.myapplication.feature_users.presentation.user_details.UserDetailsScreen
import com.sebqv97.myapplication.feature_users.presentation.user_list.components.UsersScreen
import com.sebqv97.myapplication.feature_users.presentation.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val searchUseViewModel:SearchUseViewModel = hiltViewModel()
            MyApplicationTheme {
                Scaffold(
                content = {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {

                        NavHost(
                            navController = navController,
                            startDestination = Screens.UsersScreen.route
                        ) {
                            composable(route = Screens.UsersScreen.route) {
                                UsersScreen(
                                    modifier = Modifier,
                                    navController = navController,
                                    searchBarViewModel = searchUseViewModel
                                )
                            }
                            composable(route = Screens.UserDetailScreen.route + "/{username}") {
                                UserDetailsScreen(modifier = Modifier, navController = navController)
                            }
                            composable(route = Screens.SearchUsersScreen.route){
                                SearchUsersScreen(navController = navController, modifier = Modifier, viewModel = hiltViewModel())
                            }
                        }
                    } })




            }

        }
    }


    @Composable
    @Preview
    fun DefaultPreview() {
        MyApplicationTheme {
            val navController = rememberNavController()
            val searchUserViewModel : SearchUseViewModel= hiltViewModel()
            Scaffold(
                topBar = {
                    TopBarScreen(searchUseViewModel = searchUserViewModel, modifier = Modifier, navController = navController)
                })
            {


                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    NavHost(
                        navController = navController,
                        startDestination = Screens.UsersScreen.route
                    ) {
                        composable(route = Screens.UsersScreen.route) {
                            UsersScreen(
                                modifier = Modifier,
                                navController = navController,
                                viewModel = hiltViewModel(),
                                searchBarViewModel = searchUserViewModel
                            )
                        }
                        composable(route = Screens.UserDetailScreen.route + "/{username}") {
                            UserDetailsScreen(modifier = Modifier, navController = navController)
                        }
                        composable(route = Screens.SearchUsersScreen.route){
                            SearchUsersScreen(navController = navController, modifier = Modifier, viewModel = hiltViewModel())
                        }
                    }
                }
            }


        }


    }


}
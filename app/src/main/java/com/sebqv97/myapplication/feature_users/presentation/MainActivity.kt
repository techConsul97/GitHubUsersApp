package com.sebqv97.myapplication.feature_users.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sebqv97.myapplication.feature_users.presentation.Screens
import com.sebqv97.myapplication.feature_users.presentation.TopBarScreen
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
                                )
                            }
                            composable(route = Screens.UserDetailScreen.route + "/{username}") {
                                UserDetailsScreen(modifier = Modifier, navController = navController)
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
            Scaffold(
                topBar = {
                    TopBarScreen(searchUseViewModel = hiltViewModel(), modifier = Modifier)
                })
            {


                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screens.UsersScreen.route
                    ) {
                        composable(route = Screens.UsersScreen.route) {
                            UsersScreen(
                                modifier = Modifier,
                                navController = navController,
                                viewModel = hiltViewModel()
                            )
                        }
                        composable(route = Screens.UserDetailScreen.route + "/{username}") {
                            UserDetailsScreen(modifier = Modifier, navController = navController)
                        }
                    }
                }
            }


        }


    }


}
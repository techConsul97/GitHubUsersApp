package com.sebqv97.myapplication.feature_users.presentation.user_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sebqv97.myapplication.R
import com.sebqv97.myapplication.feature_users.domain.model.UserDetailsItemModel
import com.sebqv97.myapplication.feature_users.presentation.Screens
import com.sebqv97.myapplication.feature_users.presentation.search_user.SearchUseViewModel
import com.sebqv97.myapplication.feature_users.presentation.user_details.components.UserFollowersComponent
import com.sebqv97.myapplication.feature_users.presentation.user_details.components.UserOverview
import com.sebqv97.myapplication.feature_users.presentation.user_details.components.UserReposAndIdElement
import com.sebqv97.myapplication.feature_users.utils.getWordsUseCaseErrorHandler


@Composable
fun UserDetailsScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: UserDetailsViewModel = hiltViewModel(),

) {
    val state = viewModel.state.value

    if (state.user != null) {
        state.user.let { user ->

            Column {

                UserAppBar(
                            modifier = modifier,
                    navController = navController

                )
                UserOverview(modifier = modifier.padding(bottom = 12.dp), user = user)
                UserReposAndIdElement(modifier = modifier.padding(bottom = 6.dp), user = user)
                UserFollowersComponent(modifier = modifier, user = user)
            }


        }
    } else {
        UserAppBar(
            modifier = modifier,
            navController = navController

        )
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Center) {
            Column() {

            }
            if (state.error != null) {

                val errorMessage = getWordsUseCaseErrorHandler(state.error).uppercase()
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)

                )
            }
            if (state.isLoading) {
                CircularProgressIndicator()
            }


        }
    }


}


@Composable
fun UserAppBar(modifier: Modifier, navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .background(color = MaterialTheme.colors.primaryVariant)
            .fillMaxWidth()
    ) {
        IconButton(onClick = { navController.navigate(Screens.UsersScreen.route) }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Go back to Main Screen Icon",
                tint = Color.White,
                modifier = modifier.align(CenterVertically)
            )

        }
        Text(
            text = "Profile",
            color = Color.White,
            fontWeight = FontWeight.W700,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = modifier
                .align(CenterVertically)
                .padding(end = 6.dp)
        )





    }

}


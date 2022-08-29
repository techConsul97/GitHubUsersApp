package com.sebqv97.myapplication.feature_users.presentation.user_details

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sebqv97.myapplication.feature_users.domain.model.UserDetailsItemModel
import com.sebqv97.myapplication.feature_users.presentation.Screens
import com.sebqv97.myapplication.feature_users.presentation.user_details.components.UserFollowersComponent
import com.sebqv97.myapplication.feature_users.presentation.user_details.components.UserOverview
import com.sebqv97.myapplication.feature_users.presentation.user_details.components.UserReposAndIdElement
import com.sebqv97.myapplication.feature_users.utils.getWordsUseCaseErrorHandler


@Composable
fun UserDetailsScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: UserDetailsViewModel = hiltViewModel())
{
    val state = viewModel.state.value
    state.user?.let { user ->

        Scaffold(backgroundColor = Color.White,topBar = { UserAppBar(modifier = modifier ,  navController = navController, user = state.user!!) },
            content = {
                Column {


                    UserOverview(modifier = modifier, user = user)
                    UserReposAndIdElement(modifier = modifier,user = user)
                    UserFollowersComponent(modifier = modifier, user = user)
                }
                if(state.error!= null ){
                    val errorMessage = getWordsUseCaseErrorHandler(state.error)
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)

                    )
                }
                if(state.isLoading) {
                    CircularProgressIndicator()
                }


            }

        )
    }
    }






@Composable
fun UserAppBar(modifier: Modifier,user: UserDetailsItemModel,navController: NavController) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier.padding(horizontal = 8.dp)) {
        IconButton(onClick = {navController.navigate(Screens.UsersScreen.route) }) {
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = "Go back to Main Screen Icon",
                tint = Color.White)
            
        }
        Text(text = "Profile", color = Color.White, fontWeight = FontWeight.W600, fontSize = 14.sp)
        if(user.hireable != null){
            if(user.hireable){
                Text(text = "Seeking Jobs", color = Color.White, fontWeight = FontWeight.W200, fontSize = 12.sp)

            }
            else{
                Text(text = "Not Seeking Jobs", color = Color.White, fontWeight = FontWeight.W200, fontSize = 12.sp)
            }
        }

    }
    
}

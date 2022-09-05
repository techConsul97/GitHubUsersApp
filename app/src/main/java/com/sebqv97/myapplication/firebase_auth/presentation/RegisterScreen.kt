package com.sebqv97.myapplication.firebase_auth.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sebqv97.myapplication.R
import com.sebqv97.myapplication.destinations.LoginScreenDestination
import com.sebqv97.myapplication.destinations.UsersScreenDestination
import com.sebqv97.myapplication.feature_users.presentation.ui.theme.Shapes
import com.sebqv97.myapplication.feature_users.utils.getWordsUseCaseErrorHandler
import com.sebqv97.myapplication.firebase_auth.data.model.AuthUser
import com.sebqv97.myapplication.firebase_auth.presentation.component.TextInput
import com.sebqv97.myapplication.firebase_auth.presentation.util.InputType


@Destination
@Composable
fun RegisterScreen(

    //New way of Navigation
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier,
    viewModel: FirebaseAuthViewModel = hiltViewModel()
) {

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordFocusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val user by viewModel.authState

    if(user.user != null){
        navigator.navigate(UsersScreenDestination)
    }else {


        Column(
            modifier = modifier
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = modifier.height(120.dp))
            Image(
                painter = painterResource(id = R.drawable.github_icon),
                null,
                Modifier
                    .size(80.dp)
                    .background(Color.Transparent)
                    .clip(Shapes.medium),
                contentScale = ContentScale.Crop,
                alpha = 0.5f

            ) //DONT FORGET
            TextInput(
                InputType.Name,
                getValue = { email.value = it },
                keyboardActions = KeyboardActions(
                    onNext = {
                        passwordFocusRequester.requestFocus()
                    })
            )
            TextInput(
                InputType.Password,
                getValue = { password.value = it },
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                    viewModel.createUserWithCredentials(AuthUser(email.value, password.value))
                }),
                focusRequester = passwordFocusRequester
            )


            Button(onClick = {
                viewModel.createUserWithCredentials(
                    AuthUser(
                        email.value,
                        password.value
                    )
                )
            }, modifier = modifier.fillMaxWidth()) {
                Text(text = "REGISTER", modifier = modifier.padding(vertical = 8.dp))
            }
            Divider(
                color = Color.White.copy(alpha = 0.3f),
                thickness = 1.dp,
                modifier = modifier.padding(top = 40.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Already have an account?", color = Color.White)
                TextButton(onClick =
                {
                    navigator.navigate(LoginScreenDestination)
                }
                ) {
                    Text("SIGN IN")
                }
            }

        }
        if(user.encounteredError != null){
            val errorMessage = getWordsUseCaseErrorHandler(user.encounteredError!!)
            Toast.makeText(context,errorMessage,Toast.LENGTH_SHORT).show()
        }
    }

}
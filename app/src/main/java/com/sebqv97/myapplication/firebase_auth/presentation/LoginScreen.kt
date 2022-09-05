package com.sebqv97.myapplication.firebase_auth.presentation

import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.common.api.ApiException
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sebqv97.myapplication.R
import com.sebqv97.myapplication.destinations.RegisterScreenDestination
import com.sebqv97.myapplication.destinations.UsersScreenDestination
import com.sebqv97.myapplication.feature_users.presentation.ui.theme.Shapes
import com.sebqv97.myapplication.feature_users.utils.getWordsUseCaseErrorHandler
import com.sebqv97.myapplication.firebase_auth.data.model.AuthUser
import com.sebqv97.myapplication.firebase_auth.domain.util.AuthResultContract
import com.sebqv97.myapplication.firebase_auth.presentation.auth_activities.AuthActivity
import com.sebqv97.myapplication.firebase_auth.presentation.component.ButtonInput
import com.sebqv97.myapplication.firebase_auth.presentation.component.TextInput
import com.sebqv97.myapplication.firebase_auth.presentation.util.InputType
import kotlinx.coroutines.launch


@Destination(start = true)
@Composable
fun LoginScreen(

    //New way of Navigation
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier,
    viewModel: FirebaseAuthViewModel = hiltViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()
    var text by remember { mutableStateOf<String?>(null) }
    val user by remember(viewModel) { viewModel.authState }
    val signInRequestCode = 1
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordFocusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val authResultLauncher =
        rememberLauncherForActivityResult(contract = AuthResultContract()) { task ->
            try {
                val account = task?.getResult(ApiException::class.java)
                if (account == null) {
                    text = "Google sign in failed"
                } else {
                    coroutineScope.launch {
                        viewModel.googleSignIn(
                            email = account.email!!,
                            displayName = account.displayName!!
                        )
                    }
                }
            } catch (e: ApiException) {
                text = "Google sign in failed"
            }
        }

    if (user.user != null) {
        navigator.navigate(UsersScreenDestination)

    } else {
        Column(
            modifier = modifier
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Bottom),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                    viewModel.loginUser(AuthUser(email.value, password.value))
                }),
                focusRequester = passwordFocusRequester
            )


            Button(
                onClick = { viewModel.loginUser(AuthUser(email.value, password.value)) },
                modifier = modifier.fillMaxWidth()
            ) {
                Text(text = "SIGN IN", modifier = modifier.padding(vertical = 8.dp))
            }

            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    ButtonInput(
                        imageRes = R.drawable.google_icon,
                        callbackFunction = {
                            authResultLauncher.launch(signInRequestCode)
                        },
                        modifier = modifier
                    )//GOOGLE


                }


            }
            Divider(
                color = Color.White.copy(alpha = 0.3f),
                thickness = 1.dp,
                modifier = modifier.padding(top = 40.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Don't have an account?", color = Color.Black)
                TextButton(onClick =
                {
                    // Usage of the new Navigator
                         navigator.navigate(RegisterScreenDestination)
                }
                ) {
                    Text("SIGN UP")
                }
            }
            if(user.encounteredError != null){
                val errorMessage = getWordsUseCaseErrorHandler(user.encounteredError!!)
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }

        }
    }

}


@Preview
@Composable
fun LoginPreview() {
    //LoginScreen(modifier = Modifier)

}
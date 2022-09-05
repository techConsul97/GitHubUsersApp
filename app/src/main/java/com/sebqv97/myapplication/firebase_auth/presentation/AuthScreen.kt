package com.sebqv97.myapplication.firebase_auth.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.sebqv97.myapplication.firebase_auth.data.model.AuthUser

@Destination
@Composable
fun HomeScreen(viewModel: FirebaseAuthViewModel = hiltViewModel()) {


    var email  by remember{ mutableStateOf("")}
    var password by remember {mutableStateOf("") }
    val state by viewModel.authState


    
    Column() {
        TextField(value = email, onValueChange = {email = it}, modifier = Modifier
            .fillMaxWidth()
            .heightIn(40.dp), placeholder = { Text(
            text = "Email"
        )})
        TextField(value = password, onValueChange = {password = it}, modifier = Modifier
            .fillMaxWidth()
            .heightIn(40.dp), placeholder = { Text(
            text = "Password"
        )})

        Button(onClick = { viewModel.createUserWithCredentials(AuthUser(email = email, password = password)) }) {

        }
//        if(state.isSuccessful){
//            Text(text = "User logged in", modifier = Modifier.fillMaxSize())
//        }
    }

}
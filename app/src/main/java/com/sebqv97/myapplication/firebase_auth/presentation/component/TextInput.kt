package com.sebqv97.myapplication.firebase_auth.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.graphics.Color
import com.sebqv97.myapplication.feature_users.presentation.ui.theme.Shapes
import com.sebqv97.myapplication.firebase_auth.presentation.util.InputType

@Composable
fun TextInput(
    inputType: InputType,
    getValue:(String)->Unit,
    focusRequester: FocusRequester? = null,
    keyboardActions: KeyboardActions
) {

    var value by remember {
        mutableStateOf("")
    }

    TextField(
        value = value,
        onValueChange = {
            value = it; getValue(value)},
        modifier = Modifier
            .fillMaxWidth()
            .focusOrder(focusRequester ?: FocusRequester()),
        leadingIcon = { Icon(imageVector = inputType.icon, null) },
        label = { Text(text = inputType.label) },
        shape = Shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        keyboardOptions = inputType.keyboardOptions,
        visualTransformation = inputType.visualTransformation,
        keyboardActions = keyboardActions
    )
}
package com.sebqv97.myapplication.feature_users.presentation.user_list.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sebqv97.myapplication.R
import com.sebqv97.myapplication.feature_users.presentation.Screens
import com.sebqv97.myapplication.feature_users.presentation.user_list.UserListViewModel


@OptIn(ExperimentalComposeUiApi::class)
@Composable
    fun SearchUserAppBar(
        modifier: Modifier,
        text:String,
        onTextChange:(String)->Unit,
        onCloseClick:()->Unit,
        onSearchClick:(String)->Unit,
        navController: NavController
    ) {

    val keyboardController = LocalSoftwareKeyboardController.current
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .heightIn(56.dp),
            elevation = AppBarDefaults.TopAppBarElevation,
            color = Color.White

        ) {
            TextField(
                value = text,
                onValueChange = {onTextChange(it)},
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.placeholder_search_user),
                        modifier = modifier.alpha(ContentAlpha.medium),
                        color = Color.DarkGray
                    )
                },
                textStyle = TextStyle(fontSize = MaterialTheme.typography.subtitle1.fontSize),
                singleLine = true,
                leadingIcon = {
                    IconButton(
                        modifier = modifier.alpha(ContentAlpha.medium),
                        onClick = {}
                    ){
                        Icon(imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            tint = Color.DarkGray)
                    }
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            if(text.isNotEmpty()){
                                onTextChange("")
                            }else{
                                onCloseClick()
                            }
                        }
                    ){
                        Icon(imageVector = Icons.Default.Close,
                            contentDescription = "Close Icon",
                            tint = Color.DarkGray)
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        keyboardController?.hide()


                        navController.navigate(Screens.SearchUsersScreen.route)
                        onSearchClick(text)

                    },
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    cursorColor = Color.DarkGray.copy(alpha = ContentAlpha.medium)
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester = FocusRequester())
                    //.onFocusChanged { navController.navigate(Screens.SearchUsersScreen.route) },

            )

        }

    }


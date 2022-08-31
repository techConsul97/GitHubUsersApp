package com.sebqv97.myapplication.feature_users.presentation.search_user.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.sebqv97.myapplication.R
import com.sebqv97.myapplication.feature_users.domain.model.SearchUsersItemModel
import com.sebqv97.myapplication.feature_users.domain.model.UserItemModel
import com.sebqv97.myapplication.feature_users.presentation.add_favorite.AddFavoriteUserViewModel

@Composable
fun FoundUser(
    user : SearchUsersItemModel,
    modifier: Modifier,
    onFavoriteClicked: (UserItemModel)->Unit,
    onUserClicked:(UserItemModel) -> Unit,
    addFavoriteUserViewModel: AddFavoriteUserViewModel = hiltViewModel()

){
    val userState = addFavoriteUserViewModel.state.value

    //val favoriteIconColor = if(user.isFavorite) Color.Red else Color.LightGray

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .heightIn(100.dp)
           // .clickable { onUserClicked(user) }
                //
                ,
        border = BorderStroke(1.dp, Color.LightGray),
        elevation = 8.dp
    ) {
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                AsyncImage(

                    model = user.avatarUrl,
                    contentDescription = "User Avatar",
                    error = painterResource(id = R.drawable.no_avatar),
                    modifier = modifier
                        .height(80.dp)
                        .width(80.dp)
                        .padding(start = 24.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )



                Text(
                    text =user.username!!,
                    fontSize = 24.sp,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Start,
                    modifier = modifier.padding(horizontal = 6.dp)

                )
            }

            IconButton(
                onClick = {
//                    user.updateFavoriteStatus(newValue = true)
//                    addFavoriteUserViewModel.updateUserState(user)
                },
                modifier = modifier.padding(end = 16.dp)
            ) {



                Icon(
                    imageVector = Icons.Outlined.Favorite,
                   // tint =  favoriteIconColor,
                    contentDescription ="Add favorite"
                )

            }


        }


    }

}

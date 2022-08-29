package com.sebqv97.myapplication.feature_users.presentation.user_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sebqv97.myapplication.feature_users.domain.model.UserDetailsItemModel
import com.sebqv97.myapplication.R

@Composable
fun UserReposAndIdElement(
    modifier: Modifier,
    user:UserDetailsItemModel
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.padding(8.dp)
    ) {

        UserReposElement(modifier = modifier, user = user)

        UserIdElement(modifier = modifier, user = user)
        
        UserBlogElement(modifier = modifier, user = user)


    }
}

@Composable
fun UserReposElement(modifier: Modifier,user:UserDetailsItemModel) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.repos),
            contentDescription = "Repositories Number Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = user.publicReposNumber.toString(),
            fontWeight = FontWeight.W600
        )


    }

}

@Composable
fun UserIdElement(modifier: Modifier,user:UserDetailsItemModel) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.id_photo),
            contentDescription = "GitHub Id Number",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = user.id.toString(),
            fontWeight = FontWeight.W600
        )


    }

}

@Composable
fun UserBlogElement(modifier: Modifier,user:UserDetailsItemModel) {
    if(user.blog.isNullOrEmpty()){}
    else{
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.blog),
                contentDescription = "User Blog",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape)
            )
            Text(
                text = "My Blog",
                fontWeight = FontWeight.W600
            )


        }
    }


}
@Preview
@Composable
fun UserReposAndIdElement(

) {
    UserReposAndIdElement(modifier = Modifier, user = UserDetailsItemModel(
        avatarUrl = null,
        bio = "My name is Sebastian, I am here to learn",
        location = "Alton, Hampshire",
        blog = "This is my blog",
        createdAt = null,
        email = "sebastanopolgmail.com",
        followingUrl = null,
        followers = 34,
        following = 22,
        hireable = true,
        id = 2,
        username = "sebastanopol",
        name = "Sebastian Leonti",
        reposUrl = null,
        publicReposNumber = 23,
        twitterUsername = null,
        profileUrl = null
    ) )

}
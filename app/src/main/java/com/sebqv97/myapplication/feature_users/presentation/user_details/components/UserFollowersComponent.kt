package com.sebqv97.myapplication.feature_users.presentation.user_details.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sebqv97.myapplication.feature_users.domain.model.UserDetailsItemModel

@Composable
fun UserFollowersComponent(modifier: Modifier, user: UserDetailsItemModel) {
    Box(
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        Column() {
            BioElement(modifier = modifier, user = user)
            Spacer(
                modifier = modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
                    .height(6.dp)
            )
            FollowersFollowingUsernameElement(modifier = modifier, user = user)

        }




    }


}


@Composable
fun BioElement(modifier: Modifier, user: UserDetailsItemModel) {

    Column(horizontalAlignment = Alignment.Start) {

        Text(text = "BIO", color = MaterialTheme.colors.primary)
        Text(
            text = user.bio!!,
            fontWeight = FontWeight.W200,
            fontSize = 14.sp,
            textAlign = TextAlign.Start
        )
        Button(
            onClick = { /*TODO IMPLEMENT INTENT TO OPEN WEB WHEN PRESSED*/ },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                backgroundColor = MaterialTheme.colors.primary
            ),
            shape = RoundedCornerShape(50),
            modifier = modifier.fillMaxWidth()

        ) {
            Text(text = "MY PROFILE", color = Color.White)

        }
    }

}


@Composable
fun FollowersFollowingUsernameElement(modifier: Modifier, user: UserDetailsItemModel) {


    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = modifier.padding(start = 12.dp)) {
            Text(text = "Followers", color = Color.Black)
            Text(text = user.followers.toString(), color = MaterialTheme.colors.primary)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Following", color = Color.Black)
            Text(text = user.following.toString(), color = MaterialTheme.colors.primary)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.padding(end = 12.dp)) {
            Text(text = "Username", color = Color.Black)
            Text(text = user.username.toString(), color = MaterialTheme.colors.primary)
        }


    }

}

@Preview
@Composable
fun dsf() {
    UserFollowersComponent(
        modifier = Modifier, user = UserDetailsItemModel(
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
        )
    )



}


    

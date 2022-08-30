package com.sebqv97.myapplication.feature_users.presentation.user_details.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sebqv97.myapplication.R
import com.sebqv97.myapplication.feature_users.domain.model.UserDetailsItemModel

@Composable
fun UserOverview(
    modifier: Modifier,
    user: UserDetailsItemModel
) {
    Card(modifier = modifier.fillMaxWidth(), shape = RoundedCornerShape(30.dp), elevation = 8.dp,) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 18.dp)
        ) {


            AsyncImage(

                model = user.avatarUrl,
                contentDescription = "User Avatar",
                error = painterResource(id = R.drawable.no_avatar),
                modifier = modifier
                    .height(120.dp)
                    .width(120.dp)
                    .padding(start = 24.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Text(
                text = user.name ?: "<Name Not Available>",
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
                fontSize = 22.sp
            )
            Text(
                text = user.email ?: "<Email Not Available>",
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
                fontSize = 18.sp
            )
            Box(

                modifier = modifier.padding(12.dp).border(BorderStroke(2.dp, Color.LightGray), shape = RoundedCornerShape(50))

            ) {
                Column(modifier = Modifier.padding(15.dp)) {
                    Text(
                        text = user.location ?: "Location Not Available",
                        fontWeight = FontWeight.W700,
                        fontSize = 17.sp
                    )

                }
            }


        }
    }


}


@Preview
@Composable
fun UserOverviewPreview() {
    UserOverview(
        modifier = Modifier, user = UserDetailsItemModel(
            avatarUrl = null,
            bio = "My name is Sebastian, I am here to learn",
            location = "Alton, Hampshire",
            blog = null,
            createdAt = null,
            email = "sebastanopolgmail.com",
            followingUrl = null,
            followers = 34,
            following = 22,
            hireable = true,
            id = 23,
            username = "sebastanopol",
            name = "Sebastian Leonti",
            reposUrl = null,
            publicReposNumber = 23,
            twitterUsername = null,
            profileUrl = null
        )
    )

}
package com.sebqv97.myapplication.firebase_auth.presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun ButtonInput(
    @DrawableRes imageRes:Int,
    callbackFunction:()->Unit,
    modifier: Modifier
) {
    Button(onClick = { callbackFunction() },
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
    elevation = ButtonDefaults.elevation(defaultElevation = 0.dp))
    {
        Image(painter = painterResource(id = imageRes), contentDescription = null, modifier = modifier
            .clip(
                CircleShape
            )
            .size(40.dp)
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(0.dp), contentScale = ContentScale.FillBounds)
    }

}


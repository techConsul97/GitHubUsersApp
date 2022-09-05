package com.sebqv97.myapplication.firebase_auth.util

import android.util.Patterns
import androidx.room.RoomOpenHelper

fun emailValidator(email: String?): String? {
    if (email.isNullOrBlank()) {
        return "Email was left empty"
    }
    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        return "This is not a valid Email Format"
    }
return null
}

fun passwordValidator(password:String?):String?{
    if(password.isNullOrBlank())
        return "Password left blank"

    if(password.length < 8) {
        return "Password too Short"
    }
    val containsLettersAndDigits = password.any { it.isDigit() } &&
            password.any { it.isLetter() }
    if(!containsLettersAndDigits) {
        return "Password needs to contain a letter and a digit"
    }
    return null

}



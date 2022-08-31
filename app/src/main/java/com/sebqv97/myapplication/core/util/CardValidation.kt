package com.sebqv97.myapplication.core.util

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

data class CardValidation(
    private val cardToBeValidated: String? = null,
    private val cardExpirationDate: String? = null,

) {

    private val cardTypes = hashMapOf(
        "Acme" to "1121",
        "Alfa" to "1111",
        "Amex" to "3796"
    )


    @RequiresApi(Build.VERSION_CODES.O)
     fun checkCardIntegrity():String?{
        var cardType:String? = null
        if(cardToBeValidated == null || cardExpirationDate == null || !validateCardFormat(cardToBeValidated)) return null
        if(validateCardExpirationDate(expirationDate = cardExpirationDate)){

            val lastDigits = cardToBeValidated.takeLast(4)
            if(lastDigits == cardExpirationDate.replace("/","")){
                return null
            }
            val firstDigits = cardToBeValidated.substring(0,3)
              cardType = cardTypes.entries.find { it.value == firstDigits }?.key
            if(cardType.isNullOrEmpty())
                return null


        }
        return cardType
    }



    @RequiresApi(Build.VERSION_CODES.O)
  private  fun validateCardExpirationDate(expirationDate: String): Boolean{
        //format the given date into date
        return try {

            val dateFormat = DateTimeFormatter.ofPattern("MM-yy")
            val expiryDate = LocalDate.parse(expirationDate, DateTimeFormatter.ISO_DATE)
            val comparer = expiryDate.compareTo(LocalDate.now())
            when {
                comparer > 0 -> false
                comparer < 0 -> true
                else -> false
            }

        } catch (e: DateTimeParseException) {
            Log.d("ErrorMessage",e.localizedMessage)
            false
        }

    }

    fun validateCardFormat(card:String):Boolean{

            //Just to make sure
           val  formattedCard = card.replace("-","")

            if(validateCardHasOnlyDigits(formattedCard)){
                if(validateCardLength(formattedCard)){
                    return true
                }

            }
        return false
    }

    private fun validateCardLength(card:String) = card.length == 16
    private fun validateCardHasOnlyDigits(card: String) :Boolean = card.all { it.isDigit() }
}





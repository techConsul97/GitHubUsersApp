package com.sebqv97.myapplication.feature_users.utils

import com.sebqv97.myapplication.core.util.ErrorTypes


fun getWordsUseCaseErrorHandler(errorType: ErrorTypes):String{
    //check which type of error was triggered
    return when(errorType){
        is ErrorTypes.ApiQueryTypeError -> errorType.message
        is ErrorTypes.EmptyQuery -> errorType.message
        is ErrorTypes.ProblematicHttpRequest-> errorType.message
        is ErrorTypes.EmptySearchField-> errorType.message
        is ErrorTypes.DBInsertionSuccessRetrievingFailed -> errorType.message
        is ErrorTypes.UserLoginFailed -> errorType.message
        is ErrorTypes.UserRegistrationFailed -> errorType.message
        else -> "Unexpected Error"
    }
}
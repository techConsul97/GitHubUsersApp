package com.sebqv97.myapplication.core.util

sealed class ErrorTypes {
    data class EmptySearchField(val message: String = "You left the Search Field Empty...") :
        ErrorTypes()

    data class ProblematicHttpRequest(
        var code: Int,
        val message: String = "The Http Request failed with a code of $code"
    ) : ErrorTypes()

    data class InternetConnectionFailed(val message: String = "Couldn't reach server... Check your Internet Connection") :
        ErrorTypes()

    data class EmptyQuery(val message: String = "No values to be displayed...") : ErrorTypes()
    data class ApiQueryTypeError(val message: String = "Internal problem... Tell developer not to be one anymore =:)") :
        ErrorTypes()

    data class DBInsertionSuccessRetrievingFailed(val message: String = "The insert of the word worked, but the retrieving from DB failed") :
        ErrorTypes()
}
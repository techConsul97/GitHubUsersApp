package com.sebqv97.myapplication.core.util

sealed class ResultState<T>(val data: T? = null, val errorType: ErrorTypes? = null) {
    class Success<T>(data: T) : ResultState<T>(data)
    class Error<T>(errorType: ErrorTypes, data: T? = null) : ResultState<T>(data, errorType)
    class Loading<T>(data: T? = null) : ResultState<T>(data)
}
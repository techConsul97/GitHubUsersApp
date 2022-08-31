package com.sebqv97.myapplication.feature_users.data.remote

import com.sebqv97.myapplication.feature_users.data.remote.UsersApi.Companion.SEARCH_USERS_ENDPOINT
import com.sebqv97.myapplication.feature_users.data.remote.dto.ListUsersDto
import com.sebqv97.myapplication.feature_users.data.remote.dto.SearchUsersDto
import com.sebqv97.myapplication.feature_users.data.remote.dto.UserDetailsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface UsersApi {
    @Headers("Accept:application/vnd.github+json")
    @GET(GET_USERS_ENDPOINT)
    suspend fun getAllData(): List<ListUsersDto>

    @Headers(
        "Accept: application/vnd.github+json",
        "Authorization: Bearer $API_KEY"
    )
    @GET("$GET_USERS_ENDPOINT/{specificUser}")
    suspend fun getDataFromPath (@Path("specificUser") specificUser:String):Response<UserDetailsDto>

    @Headers(
        "Accept: application/vnd.github+json",
        "Authorization: Bearer $API_KEY"
    )
    @GET(SEARCH_USERS_ENDPOINT)
    suspend fun searchUsersBasedOnQuery(@Query("q")searchQuery:String):Response<SearchUsersDto>






    companion object {
        const val BASE_URL = " https://api.github.com/"
        const val GET_USERS_ENDPOINT = "users"
        const val SEARCH_USERS_ENDPOINT = "search/users"
        const val API_KEY = "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"
    }
}
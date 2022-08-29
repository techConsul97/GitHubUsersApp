package com.sebqv97.myapplication.feature_users.data.repository

import com.sebqv97.myapplication.feature_users.data.local.entity.UserEntity
import com.sebqv97.myapplication.feature_users.data.remote.UsersApi
import com.sebqv97.myapplication.feature_users.data.remote.dto.ListUsersDto
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import retrofit2.Response

class UsersRepositoryImplTest {

    @Mock
    private lateinit var usersApi: UsersApi

    private var users  = mutableListOf<UserEntity>()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        users = mutableListOf()
    }



    @Test
    fun `call users api CORRECTLY expect SUCCESS`() {
        runBlocking {
            whenever(usersApi.getAllData()).thenReturn(listOf())
           val response = usersApi.getAllData()
            assertEquals(0, response.size)
        }

    }

    @Test
    fun `call user api with WRONG path and expect FAIL`() {
        val wrongPath = "dsfsdfdsrtert"
        runBlocking{
            whenever(usersApi.getDataFromPath(wrongPath)).thenReturn(Response.error(404,"fdds".toResponseBody()))
            val response = usersApi.getDataFromPath(wrongPath)
               if(response.isSuccessful){
                   assert(false)
               }
                assertEquals(404,response.code())
            }
        }

}
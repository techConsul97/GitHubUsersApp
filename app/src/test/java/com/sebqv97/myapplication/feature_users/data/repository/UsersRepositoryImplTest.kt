package com.sebqv97.myapplication.feature_users.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sebqv97.myapplication.feature_users.data.local.UsersDao
import com.sebqv97.myapplication.feature_users.data.local.entity.UserEntity
import com.sebqv97.myapplication.feature_users.data.remote.UsersApi
import com.sebqv97.myapplication.feature_users.data.remote.dto.ListUsersDto
import com.sebqv97.myapplication.feature_users.data.remote.dto.UserDetailsDto
import com.sebqv97.myapplication.feature_users.domain.repository.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.whenever
import retrofit2.HttpException
import retrofit2.Response

@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class UsersRepositoryImplTest {
private var repository: UsersRepository? = null
    @Mock
    private lateinit var usersApi: UsersApi
    @Mock private lateinit var usersDao:UsersDao

    private var users = mutableListOf<UserEntity>()
    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val rule: TestRule =
        InstantTaskExecutorRule()   //tell the application to run the tests INSTANTLY, HIGH PRIORITY

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = UsersRepositoryImpl(usersApi,usersDao)
        Dispatchers.setMain(testDispatcher)

    }

    @After
    fun teardown(){
        repository = null
        Dispatchers.resetMain()
    }

    @Test
    fun `simulate Internet Connection Problem Expect Failed Status`(){
        runBlocking {
            whenever(usersApi.getAllData()).doAnswer{
                throw HttpException(Response.error<List<ListUsersDto>>(12002,"".toResponseBody()))
            }
            whenever(usersApi.getDataFromPath("seba"))doAnswer{
                throw HttpException(Response.error<UserDetailsDto>(12002,"".toResponseBody()))
            }



            //Now, let's test the second API
            val response2 = repository!!.getUser("seba")
            assertEquals(12002,response2.code())
        }
    }



}
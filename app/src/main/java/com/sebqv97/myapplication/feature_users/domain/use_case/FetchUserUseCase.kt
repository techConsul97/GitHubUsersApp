package com.sebqv97.myapplication.feature_users.domain.use_case

import com.sebqv97.myapplication.core.util.ErrorTypes
import com.sebqv97.myapplication.core.util.Resource
import com.sebqv97.myapplication.feature_users.domain.model.SearchUsersModel
import com.sebqv97.myapplication.feature_users.domain.repository.UsersRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.util.concurrent.CancellationException
import javax.inject.Inject

class FetchUserUseCase @Inject constructor(
    private val repository: UsersRepository

) {
    operator fun invoke(searchedUserByUserName: String?): Flow<Resource<SearchUsersModel>> = flow {

        if (searchedUserByUserName.isNullOrEmpty()) {
            emit(Resource.Error<SearchUsersModel>(ErrorTypes.EmptySearchField()))
        } else {
            try {
                emit(Resource.Loading())
                delay(500)
                coroutineScope {
                    val deferredResponse =
                        async { repository.searchUsersByQuery(givenQuery = searchedUserByUserName) }
                    val response = deferredResponse.await()
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            emit(Resource.Success(response.body()!!.toSearchUsersModel()))
                        } else {
                            emit(Resource.Error(ErrorTypes.EmptyQuery()))
                        }

                    } else {
                        val errorCode = response.code()
                        emit(Resource.Error(ErrorTypes.ProblematicHttpRequest(errorCode)))
                    }

                }
            } catch (e: IOException) {
                emit(Resource.Error(ErrorTypes.InternetConnectionFailed()))
            } catch (e: CancellationException) {
                emit(Resource.Error(ErrorTypes.JobCancellationError()))
            }
        }

    }


}
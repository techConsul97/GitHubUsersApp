package com.sebqv97.myapplication.feature_users.domain.use_case

import com.sebqv97.myapplication.core.util.ErrorTypes
import com.sebqv97.myapplication.core.util.ResultState
import com.sebqv97.myapplication.feature_users.domain.model.UserItemModel
import com.sebqv97.myapplication.feature_users.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
   private val usersRepository: UsersRepository
) {
    operator fun invoke(): Flow<ResultState<List<UserItemModel>>> = flow {

        //call the api
        try {
            emit(ResultState.Loading())
            val usersResponseList = usersRepository.getUsers()
            if (usersResponseList.isEmpty()) {
                emit(ResultState.Error<List<UserItemModel>>(ErrorTypes.ApiQueryTypeError()))
            } else {
//                val usersListEntity = usersResponseList.map { it.toUserEntity() }
//                //insert into the database
//                usersRepository.deleteUsers()
//                usersRepository.insertUsers(usersListEntity)
                emit(ResultState.Success(data = usersResponseList.map { it.toUserItemModel() }))


            }
        } catch (e: HttpException) {
            val httpErrorCode = e.code()
            emit(ResultState.Error(ErrorTypes.ProblematicHttpRequest(httpErrorCode)))
        } catch (e: IOException) {
            emit((ResultState.Error(ErrorTypes.InternetConnectionFailed())))
        }
        //get data from db after it was inserted



//        usersRepository.readUsers().collect{ data->
//            if(data.isEmpty()){
//                emit(ResultState.Error<List<UserItemModel>>(ErrorTypes.DBInsertionSuccessRetrievingFailed()))
//            }else{
//                emit(ResultState.Success(data.map { it.toUserItemModel() }))
//            }
//        }

    }

}
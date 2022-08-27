package com.sebqv97.myapplication.feature_users.domain.use_case

import com.sebqv97.myapplication.core.util.ErrorTypes
import com.sebqv97.myapplication.core.util.Resource
import com.sebqv97.myapplication.feature_users.domain.model.UserItemModel
import com.sebqv97.myapplication.feature_users.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
   private val usersRepository: UsersRepository
) {
    operator fun invoke(): Flow<Resource<List<UserItemModel>>> = flow {

        //call the api
        try {
            emit(Resource.Loading())
            val usersResponseList = usersRepository.getUsers()
            if (usersResponseList.isEmpty()) {
                emit(Resource.Error<List<UserItemModel>>(ErrorTypes.ApiQueryTypeError()))
            } else {
                val usersListEntity = usersResponseList.map { it.toUserEntity() }
                //insert into the database
                usersRepository.deleteUsers()
                usersRepository.insertUsers(usersListEntity)


            }
        } catch (e: HttpException) {
            val httpErrorCode = e.code()
            emit(Resource.Error(ErrorTypes.ProblematicHttpRequest(httpErrorCode)))
        } catch (e: IOException) {
            emit((Resource.Error(ErrorTypes.InternetConnectionFailed())))
        }
        //get data from db after it was inserted



        usersRepository.readUsers().collect{ data->
            if(data.isEmpty()){
                emit(Resource.Error<List<UserItemModel>>(ErrorTypes.DBInsertionSuccessRetrievingFailed()))
            }else{
                emit(Resource.Success(data.map { it.toUserItemModel() }))
            }
        }

    }

}
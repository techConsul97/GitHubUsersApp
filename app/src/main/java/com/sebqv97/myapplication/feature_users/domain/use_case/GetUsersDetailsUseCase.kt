package com.sebqv97.myapplication.feature_users.domain.use_case

import com.sebqv97.myapplication.core.util.ErrorTypes
import com.sebqv97.myapplication.core.util.ResultState
import com.sebqv97.myapplication.feature_users.domain.model.UserDetailsItemModel
import com.sebqv97.myapplication.feature_users.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetUsersDetailsUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {

    operator fun invoke(searchedUsername:String): Flow<ResultState<UserDetailsItemModel>> = flow {

        //call the api
        try {
            emit(ResultState.Loading())
            val searchedUserResponse = usersRepository.getUser(searchedUsername)
            if(searchedUserResponse.isSuccessful){

                searchedUserResponse.body().let {
                    val userDetailsEntity = it!!.toUserDetailsEntity()
                    //delete if exists
                    usersRepository.deleteUser(userDetailsEntity)
                    //insert into db
                    usersRepository.insertUser(userDetailsEntity)


                }

            }else{
                val errorCode = searchedUserResponse.code()
               emit(ResultState.Error(ErrorTypes.ProblematicHttpRequest(errorCode)))
            }

        }catch (e:IOException){
            emit(ResultState.Error(ErrorTypes.InternetConnectionFailed()))
        }

        //Search it and retrieve it
        usersRepository.readSpecificUser(searchedUsername).collect{ foundUser->
            if(foundUser.equals(null)){
                emit(ResultState.Error<UserDetailsItemModel>(ErrorTypes.DBInsertionSuccessRetrievingFailed()))
            }else{
                emit(ResultState.Success(foundUser.toUserDetailsItemModel()))
            }

        }
    }
}
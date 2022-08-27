package com.sebqv97.myapplication.feature_users.domain.use_case

import com.sebqv97.myapplication.core.util.ErrorTypes
import com.sebqv97.myapplication.core.util.Resource
import com.sebqv97.myapplication.feature_users.data.repository.UsersRepositoryImpl
import com.sebqv97.myapplication.feature_users.domain.model.UserDetailsItemModel
import com.sebqv97.myapplication.feature_users.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUsersDetailsUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {

    operator fun invoke(searchedUsername:String): Flow<Resource<UserDetailsItemModel>> = flow {

        //call the api
        try {
            emit(Resource.Loading())
            val searchedUserResponse = usersRepository.getUser(searchedUsername)
            if(searchedUserResponse.isSuccessful){

                searchedUserResponse.body().let {
                    val userDetailsEntity = it!!.toUserDetailsEntity()
                    //delete if exists
                    usersRepository.deleteUser(userDetailsEntity)
                    //insert into db
                    usersRepository.insertUser(userDetailsEntity)


                    //Search it and retrieve it
                  usersRepository.readSpecificUser(searchedUsername).collect{ foundUser->
                     if(foundUser.equals(null)){
                         emit(Resource.Error<UserDetailsItemModel>(ErrorTypes.DBInsertionSuccessRetrievingFailed()))
                     }else{
                         emit(Resource.Success(foundUser.toUserDetailsItemModel()))
                     }

                  }

                }

            }else{
                val errorCode = searchedUserResponse.code()
               emit(Resource.Error(ErrorTypes.ProblematicHttpRequest(errorCode)))
            }

        }catch (e:IOException){
            emit(Resource.Error(ErrorTypes.InternetConnectionFailed()))
        }
    }
}
package com.joel.car_compose.model.auth

import android.content.SharedPreferences
import com.joel.car_compose.model.network.ApiService
import com.joel.car_compose.utils.ApiConstants
import retrofit2.HttpException

class AuthRepoImplemention (
    private val apiService: ApiService,
    private val prefs : SharedPreferences,
    ) : AuthRepo {

    override fun register(
        userName: String,
        password: String,
        email: String,
        location: String,
        phoneNumber: String,
    ): AuthResult<Unit> {
       return try {
         val response = apiService.register(
               request = RegisterRequest(
                   location = location,
                   email = email,
                   number = phoneNumber,
                   name = userName,
                   password = password

               )
           )
           prefs.edit()
               .putString(ApiConstants.USER_TOKEN, response.token)
               .apply()

           AuthResult.Authorized()
       }
       catch (e : HttpException){
           if (e.code() == 401){
               AuthResult.UnAuthorized()
           }
           else
               AuthResult.UnknownError()
       }
        catch (e : Exception){
            AuthResult.UnknownError()
        }
    }

    override fun login(userName: String, password: String): AuthResult<Unit> {
        return try {
            apiService.login(
                request = LoginRequest(
                    username = userName,
                    password = password
                )
            )
            AuthResult.Authorized()
        }
        catch (e : HttpException){
            if (e.code() == 401){
                AuthResult.UnAuthorized()
            }
            else
                AuthResult.UnknownError()
        }
        catch (e : Exception){
            AuthResult.UnknownError()
        }
    }

    override fun authenticate(): AuthResult<Unit> {
        return try {
        val token =   prefs.getString(ApiConstants.USER_TOKEN, "") ?: return AuthResult.Authorized()
             apiService.authenticate("Token $token")


            AuthResult.Authorized()
        }
        catch (e : HttpException){
            if (e.code() == 401){
                AuthResult.UnAuthorized()
            }
            else
                AuthResult.UnknownError()
        }
        catch (e : Exception){
            AuthResult.UnknownError()
        }
    }


}
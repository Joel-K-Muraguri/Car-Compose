package com.joel.car_compose.model.auth

import android.content.SharedPreferences
import android.util.Log
import com.joel.authentication_compose.auth.AuthResult
import com.joel.car_compose.model.network.ApiService
import com.joel.car_compose.utils.ApiConstants
import retrofit2.HttpException

class AuthRepoImplementation(
    private val apiService: ApiService,
    private val prefs: SharedPreferences,

    ) : AuthRepo {
    override suspend fun login(userName: String, password: String): AuthResult<Unit> {
        return try {
            apiService.login(
                request = LogInRequest(
                    username = userName,
                    password = password
                )
            )
            AuthResult.Authorized()
        }
        catch (e: HttpException){
            Log.d("TEST::", "login:  Unauthorized"+e.message)
            if (e.code() == 401){
                AuthResult.Unauthorized()
            }
            else
                AuthResult.UnknownError()
        }
        catch (e : Exception){
            Log.d("TEST::", "login: Unknown error ")
            AuthResult.UnknownError()
        }
    }

    override suspend fun register(
        userName: String,
        email: String,
        phoneNumber: String,
        location: String,
        password: String,
    ): AuthResult<Unit> {
       return try {
           val response =  apiService.register(
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
               AuthResult.Unauthorized()
           }
           else
               AuthResult.UnknownError()
       }
        catch (e: Exception){
            AuthResult.UnknownError()
        }
    }

    override suspend fun authenticate(): AuthResult<Unit> {
        return try{
            val token = prefs.getString(ApiConstants.USER_TOKEN, "") ?: return AuthResult.Unauthorized()
            apiService.authenticate("Token $token")
            AuthResult.Authorized()
        }
        catch (e : HttpException){
            if (e.code() == 401){
                AuthResult.Unauthorized()
            }
            else
                AuthResult.UnknownError()
        }
        catch (e : Exception){
            AuthResult.UnknownError()
        }
    }
}
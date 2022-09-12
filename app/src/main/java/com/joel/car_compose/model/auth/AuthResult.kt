package com.joel.car_compose.model.auth

sealed class AuthResult<T>(val data : T? = null){
    class Authorized<T>(data: T? = null) : AuthResult<T>()
    class UnAuthorized<T>() : AuthResult<T>()
    class UnknownError<T>() : AuthResult<T>()

}

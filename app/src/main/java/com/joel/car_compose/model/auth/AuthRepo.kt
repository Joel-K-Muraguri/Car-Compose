package com.joel.car_compose.model.auth

interface AuthRepo {

    fun register(
        userName : String,
        password : String,
        email : String,
        location : String,
        phoneNumber : String
    ) : AuthResult<Unit>

    fun login(
        userName: String,
        password: String
    ) : AuthResult<Unit>

    fun authenticate() : AuthResult<Unit>

}
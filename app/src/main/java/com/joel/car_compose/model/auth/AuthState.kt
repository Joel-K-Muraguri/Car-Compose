package com.joel.car_compose.model.auth

data class AuthState(
    val isUserNameChangedLogIn : String = "",
    val isPasswordChangedLogIn : String = "",

    val isUserNameChangedSignIn : String = "",
    val isPhoneNumberChangedSignIn : String = "",
    val isEmailChangedSignIn : String = "",
    val isLocationChangedSignIn : String = "",
    val isPasswordChangedSignIn : String = "",
    val isLoading : Boolean = false

)

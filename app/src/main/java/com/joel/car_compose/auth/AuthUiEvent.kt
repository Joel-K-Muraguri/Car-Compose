package com.joel.car_compose.auth

sealed class AuthUiEvent(){
    data class RegisterUserNameChanged(val value: String) : AuthUiEvent()
    data class RegisterPhoneChanged(val value: String) : AuthUiEvent()
    data class RegisterEmailChanged(val value: String) : AuthUiEvent()
    data class RegisterLocationChanged(val value: String) : AuthUiEvent()
    data class RegisterPasswordChanged(val value: String) : AuthUiEvent()
    object Register : AuthUiEvent()

    data class LogInUserNameChanged(val value: String) : AuthUiEvent()
    data class LogInPasswordChanged(val value: String) : AuthUiEvent()
    object LogIn : AuthUiEvent()
}

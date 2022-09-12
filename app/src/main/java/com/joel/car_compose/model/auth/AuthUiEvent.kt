package com.joel.car_compose.model.auth

sealed class AuthUiEvent(){

    data class IsPasswordLogInChanged(val password : String) : AuthUiEvent()
    data class IsUserNameLogInChanged(val userName : String) : AuthUiEvent()
    object LogIn : AuthUiEvent()


    data class IsUserNameSignInChanged(val userName : String) : AuthUiEvent()
    data class IsLocationSignInChanged(val location : String) : AuthUiEvent()
    data class IsEmailSignInChanged(val email : String) : AuthUiEvent()
    data class IsPhoneNumberSignInChanged(val phoneNumber : String) : AuthUiEvent()
    data class IsPasswordSignInChanged(val password : String) : AuthUiEvent()
    object SignIn : AuthUiEvent()


}

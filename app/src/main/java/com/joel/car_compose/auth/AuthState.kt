package com.joel.car_compose.auth

data class AuthState(
    val isLoading: Boolean = false,
    val RegisterUsername: String = "",
    val RegisterPassword: String = "",
    val RegisterPhoneNumber : String = "",
    val RegisterLocation : String = "",
    val RegisterEmail: String = "",
    val LogInUsername: String = "",
    val LogInInPassword: String = ""
)

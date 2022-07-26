package com.joel.car_compose.auth

data class TokenResponse(
    val token: String,
    val id: Byte,
    val username: String,
    val email: String,
    val first_name: String,
    val last_name: String,
    val phone_number: String,
    val address: String,
)

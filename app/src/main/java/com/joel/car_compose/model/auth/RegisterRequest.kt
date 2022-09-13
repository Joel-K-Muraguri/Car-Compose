package com.joel.car_compose.model.auth

data class RegisterRequest(
    val location: String,
    val email: String,
    val number: String,
    val name: String,
    val password : String,
)

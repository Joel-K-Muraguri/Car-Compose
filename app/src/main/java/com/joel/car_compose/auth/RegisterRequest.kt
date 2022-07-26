package com.joel.car_compose.auth

// number, password, email, name, location
data class RegisterRequest(
    var address: String,
    var email: String,
    var phone_number: String,
    var username: String,
    var password: String

)

package com.joel.car_compose.auth

// number, password, email, name, location
data class RegisterRequest(
    var location: String,
    var email: String,
    var number: String,
    var name: String,
    var password: String

)

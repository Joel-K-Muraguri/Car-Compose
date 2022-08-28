package com.joel.car_compose.model.network.auth

// number, password, email, name, location
data class RegisterRequest(
    var location: String,
    var email: String,
    var number: String,
    var name: String,
    var password: String

)

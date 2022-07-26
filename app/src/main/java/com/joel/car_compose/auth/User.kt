package com.joel.car_compose.auth

data class User(
    var address: String,
    var email: String,
    var first_name: String,
    var id: Int,
    var last_name: String,
    var phone_number: String,
    var token: String,
    var username: String,

)
package com.joel.car_compose.network

sealed class NetworkStatus<T> (val data: T? = null, val message: String? = null){

    class Success<T> (data: T) : NetworkStatus<T>(data)
    class Error<T> (message: String, data: T?= null) : NetworkStatus<T>(data, message)
    class Loading<T> (data: T? = null) : NetworkStatus<T>(data)

}

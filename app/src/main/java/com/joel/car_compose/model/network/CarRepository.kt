package com.joel.car_compose.model.network

import com.joel.car_compose.model.network.ApiService
import javax.inject.Singleton

@Singleton
class CarRepository(
    private val apiService: ApiService
) {

    suspend fun getCarInfo(){

    }



}
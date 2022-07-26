package com.joel.car_compose.network

import com.joel.car_compose.model.Car
import com.joel.car_compose.model.Brand
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class CarRepository @Inject constructor(
    private val apiService: ApiService
) {

    //TODO: Make this return Mutable Live Data
    suspend fun getAutoList() : NetworkStatus<List<Car>> {
        val autoListResponse = try {
            apiService.getAutoList()
        }
        catch (e : Exception){
            return NetworkStatus.Error("An Unknown Error Occurred")

        }
        return NetworkStatus.Success(autoListResponse)

    }

    suspend fun getBrandList() : NetworkStatus<List<Brand>> {
        val brandListResponse = try {
            apiService.getBrandList()
        }
        catch (e : Exception){
            return NetworkStatus.Error("An Unknown Error Occurred")
        }
        return NetworkStatus.Success(brandListResponse)

    }
}
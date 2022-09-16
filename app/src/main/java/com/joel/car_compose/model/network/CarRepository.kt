package com.joel.car_compose.model.network

import com.joel.car_compose.model.data.CarItem
import retrofit2.HttpException
import javax.inject.Inject

class CarRepository @Inject constructor(
    private val apiService: ApiService
){

    suspend fun getCarInfo(carId : Int): Resource<CarItem>{
        val response = try {
            apiService.getCarInfo(
                id = carId
            )
        }
        catch (e:HttpException){
            if (e.code() == 401){
             return  Resource.Error(message = "Unauthorized")
            }
            else if (e.code() == 500){
                return  Resource.Error(message = "Internal Server Error")

            }
            else{
                return Resource.Error(message = "Check Internet Connection")
            }
        }
        return Resource.Success(response)
    }
}
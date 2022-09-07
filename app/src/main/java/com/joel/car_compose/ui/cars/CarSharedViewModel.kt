package com.joel.car_compose.ui.cars

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joel.car_compose.model.data.Brand
import com.joel.car_compose.model.network.ApiService
import com.joel.car_compose.model.auth.SessionManager
import com.joel.car_compose.model.data.CarItem
import com.joel.car_compose.model.fav.FavouriteResponseItem
import kotlinx.coroutines.launch


class CarSharedViewModel(
context: Context
): ViewModel() {


    var carListResponse: List<CarItem> by mutableStateOf(listOf())
    var brandListResponse: List<Brand> by mutableStateOf(listOf())
    var favouriteListResponse : List<FavouriteResponseItem> by mutableStateOf(listOf())
    private var errorMessage: String by mutableStateOf("An Unknown Error Occurred")


            fun getCarData() {
                viewModelScope.launch {
                    val apiService = ApiService.getInstance()
                    try {
                        val carList = apiService.getCarList()
                        carListResponse = carList
//                        Log.d("TEST::", "getCarData: " + carList.size)
                    } catch (e: Exception) {
                        errorMessage = e.message.toString()
//                        Log.d("TEST::", "getCarData: " + errorMessage)
                    }
                }
            }


            fun getBrandData() {
                viewModelScope.launch {
                    val apiService = ApiService.getInstance()
                    try {
                        val brandList = apiService.getBrandList()
                        brandListResponse = brandList

                    } catch (e: Exception) {
                        errorMessage = e.message.toString()

                    }
                }
            }

     fun getFavourites(context: Context){
        val sessionManager = SessionManager(context)
         viewModelScope.launch {
             val apiService = ApiService.getInstance()
             try {
                 val favouriteList = apiService.fetchFavourites(token = "Token ${sessionManager.fetchAuthToken()}")
                 favouriteListResponse = favouriteList

             }
             catch (e: Exception){
                 errorMessage =  e.message.toString()
             }
         }
    }


    fun getCarDetails(context: Context,car: CarItem){
        val sessionManager = SessionManager(context)
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                val carDetails = apiService.fetchCarDetails(car.id)

            }
            catch (e: Exception){
                errorMessage = e.message.toString()
            }
        }
    }

}




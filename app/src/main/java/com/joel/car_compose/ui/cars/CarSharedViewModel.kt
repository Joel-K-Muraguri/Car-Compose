package com.joel.car_compose.ui.cars

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joel.car_compose.model.Car
import com.joel.car_compose.model.Brand
import com.joel.car_compose.network.ApiService
import com.joel.car_compose.utils.Routes
import com.joel.car_compose.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class CarSharedViewModel @Inject constructor(

): ViewModel() {

    //TODO: Add MutableLiveData Return type
    //TODO: Add Function with LiveData return type to be used in the CarListScreen

    var carListResponse: List<Car> by mutableStateOf(listOf())
    var brandListResponse: List<Brand> by mutableStateOf(listOf())
    private var errorMessage: String by mutableStateOf("An Unknown Error Occurred")


            fun getCarData() {
                viewModelScope.launch {
                    val apiService = ApiService.getInstance()
                    try {
                        val carList = apiService.getAutoList()
                        carListResponse = carList
                        Log.d("TEST::", "getCarData: " + carList.size)
                    } catch (e: Exception) {
                        errorMessage = e.message.toString()
                        Log.d("TEST::", "getCarData: " + errorMessage)
                    }
                }
            }

            fun getBrandData() {
                viewModelScope.launch {
                    val apiService = ApiService.getInstance()
                    try {
                        val brandList = apiService.getBrandList()
                        brandListResponse = brandList
                        //     Log.d("TEST::", "getBrandData: "+brandList.size)

                    } catch (e: Exception) {
                        errorMessage = e.message.toString()
                        //        Log.d("TEST::", "getBrandData: "+errorMessage)
                    }
                }
            }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvents(events: ListScreenEvents) {
        when (events) {
            is ListScreenEvents.OnCarCardClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.DETAILED_SCREEN + "?carId=${events.car.id}"))
            }
            is ListScreenEvents.OnAddToFavourite -> {


            }
            is ListScreenEvents.OnBrandSortClick -> {

            }
            is ListScreenEvents.OnSearch -> {

            }
        }
    }


    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
      }
    }

}




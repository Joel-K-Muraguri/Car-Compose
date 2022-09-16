package com.joel.car_compose.viewmodel

import androidx.lifecycle.ViewModel
import com.joel.car_compose.model.data.CarItem
import com.joel.car_compose.model.network.CarRepository
import com.joel.car_compose.model.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CarDetailsViewModel @Inject constructor(
    private val repository: CarRepository
) : ViewModel() {
    suspend fun getCarInfo(carId : Int) : Resource<CarItem>{
        return repository.getCarInfo(carId = carId)
    }
}
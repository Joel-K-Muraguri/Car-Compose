package com.joel.car_compose.utils

import com.joel.car_compose.model.Car

sealed class UiEvent(){
    object PopBackStack : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    data class SnackBar(
        val message: String,
        val action : String?= null
    ) : UiEvent()
    data class Favourite(val car: Car) : UiEvent()


}

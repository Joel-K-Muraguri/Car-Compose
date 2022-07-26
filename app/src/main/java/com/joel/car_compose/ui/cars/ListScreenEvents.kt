package com.joel.car_compose.ui.cars

import com.joel.car_compose.model.Car

sealed class ListScreenEvents{
    data class OnAddToFavourite(val car: Car) : ListScreenEvents()
    object OnSearch : ListScreenEvents()
    object OnBrandSortClick : ListScreenEvents()
    data class OnCarCardClick(val car: Car) : ListScreenEvents()
//    data class OnDetailScreenView(val car: Car) : ListScreenEvents()

}

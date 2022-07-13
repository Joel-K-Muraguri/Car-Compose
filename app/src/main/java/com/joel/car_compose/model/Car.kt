package com.joel.car_compose.model

import androidx.compose.ui.graphics.painter.Painter

/*Car Images about 3-5,
Car Overview
Car horsepower(engine cc)
Car price
Car Brand
Car Model
Car Fuel Type
Car Transmission(manual/auto)
Year of manufacture */

data class Car(
    val id: Int,
    val overView: String,
    val horsPower: Int,
    val price: Double,
    val brand: String,
    val model: String,
    val fuelType: String,
    val imageURL: Int,
    val year: Int,

    )

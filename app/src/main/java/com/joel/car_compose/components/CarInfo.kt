package com.joel.car_compose.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.joel.car_compose.model.data.CarItem

@Composable
fun CarInfo(
    car: CarItem
){
    Column{
        Text(
            text = car.name,
            style = MaterialTheme.typography.h5
        )
        Row() {
            Text(
                text = car.fuel
            )
        }
    }
}
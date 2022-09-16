package com.joel.car_compose.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CarItem(
    val brand: Int,
    val color: String,
    val fuel: String,
    val horsepower: String,
    val id: Int,
    val image: String,
    val model: String,
    val name: String,
    val overview: String,
    val price: Double,
    val quantity: Int,
    val transmission: Int,
    val year_of_manufacture: Int
) : Parcelable
package com.joel.car_compose.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Car(
    val id: Int,
    val name: String,
    val overView : String?,
    val horsepower : String,
    val price : Double,
    val image : String,
    val model : String,
    val fuel : String,
    val quantity : Int,
    val color: String,
    val transmission: Int,
    val year_of_manufacture : Int,
    val brand : Int,
) : Parcelable

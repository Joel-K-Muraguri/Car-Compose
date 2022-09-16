package com.joel.car_compose.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BrandItem(
    val id: Int,
    val logo: String,
    val name: String
) : Parcelable
package com.joel.car_compose.components

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joel.car_compose.model.network.ApiService
import com.joel.car_compose.model.auth.SessionManager
import com.joel.car_compose.model.fav.FavouriteCarItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun Favourite(
    context: Context,
    carId: Int
){
    Surface(
        modifier =  Modifier.
                size(40.dp),
        shape = CircleShape,
        color = MaterialTheme.colors.background
    ) {
        FavouriteButton()
    }

}

@Composable
fun FavouriteButton(


){
    val isCheckedState = remember {
        mutableStateOf(false)
    }


    IconToggleButton(
        checked = isCheckedState.value,
        onCheckedChange = {
            isCheckedState.value = !isCheckedState.value
        },

        modifier = Modifier
            .padding(10.dp)
            .clickable( onClick = {

            })
    ) {

        val transition = updateTransition(isCheckedState.value, label = "")
        val tint by transition.animateColor(label = "iconColor") { isChecked ->

            if (isChecked) Color.Red else Color.Black
        }


        Icon(
            imageVector = if (isCheckedState.value) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = "Icon",
            tint = tint,

        )
    }
}

 fun toggleFavourite(context: Context, carId: Int){
    val apiService = ApiService.getInstance()
    val sessionManager = SessionManager(context)
     Toast.makeText(context, "Loading..", Toast.LENGTH_SHORT).show()
    apiService.toggleFavoriteCar("Token ${sessionManager.fetchAuthToken()}", carId)
        .enqueue(object: Callback<FavouriteCarItem>{
        override fun onResponse(
            call: Call<FavouriteCarItem>,
            response: Response<FavouriteCarItem>,
        ) {
            if (response.code() == 200){
                Toast.makeText(context,response.body()!!.message, Toast.LENGTH_SHORT).show()
            }
            else if (response.code() == 401){
                Toast.makeText(context,"Unauthorized", Toast.LENGTH_SHORT).show()

            }
            else
                Toast.makeText(context,"Something Went Wrong", Toast.LENGTH_SHORT).show()

        }

        override fun onFailure(call: Call<FavouriteCarItem>, t: Throwable) {
            Toast.makeText(context, "Check your Internet Connection", Toast.LENGTH_SHORT).show()
            TODO("Not yet implemented")
        }

    })

}

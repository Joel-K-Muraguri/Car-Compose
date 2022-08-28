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
import com.joel.car_compose.model.network.auth.SessionManager
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
        color = Color(0xFFFFFFFF)
    ) {
        FavouriteButton(context, carId)
    }

}

@Composable
fun FavouriteButton(
    context: Context,
    carId: Int

){
    val isCheckedState = remember {
        mutableStateOf(false)
    }


    IconToggleButton(
        // on below line we are
        // specifying default check state
        checked = isCheckedState.value,
        // on below line we are adding on check change
        onCheckedChange = {
            isCheckedState.value = !isCheckedState.value
        },
        // on below line we are adding a padding
        modifier = Modifier
            .padding(10.dp)
            .clickable( onClick = {
                toggleFavourite(context,carId)
            })
    ) {
        // on below line we are creating a
        // variable for our transition
        val transition = updateTransition(isCheckedState.value, label = "")

        // on below line we are creating a variable for
        // color of our icon
        val tint by transition.animateColor(label = "iconColor") { isChecked ->
            // if toggle button is checked we are setting color as red.
            // in else condition we are setting color as black
            if (isChecked) Color.Green else Color.Black
        }

//        // om below line we are specifying transition
//        val size by transition.animateDp(
//            transitionSpec = {
//                // on below line we are specifying transition
//                if (false isTransitioningTo true) {
//                    // on below line we are specifying key frames
//                    keyframes {
//                        // on below line we are specifying animation duration
//                        durationMillis = 250
//                        // on below line we are specifying animations.
//                        30.dp at 0 with LinearOutSlowInEasing // for 0-15 ms
//                        35.dp at 15 with FastOutLinearInEasing // for 15-75 ms
//                        40.dp at 75 // ms
//                        35.dp at 150 // ms
//                    }
//                } else {
//                    spring(stiffness = Spring.StiffnessVeryLow)
//                }
//            },
//            label = "Size", )
//

        // on below line we are creating icon for our toggle button.
        Icon(
            // on below line we are specifying icon for our image vector.
            imageVector = if (isCheckedState.value) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = "Icon",
            // on below line we are specifying
            // tint for our icon.
            tint = tint,
            // on below line we are specifying
            // size for our icon.
       //     modifier = Modifier.size(size)
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

//@Preview
//@Composable
//fun FavouriteButtonPreview(){
//    Favourite()
//}
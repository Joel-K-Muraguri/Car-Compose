package com.joel.car_compose.components

import androidx.compose.material.TextField
import androidx.compose.runtime.*

@Composable
fun InputTextField(
    label: String,
){
    var value by remember {
        mutableStateOf("")
    }

    TextField(
        value = value ,
        onValueChange = {
            value = it
        }
    )


}
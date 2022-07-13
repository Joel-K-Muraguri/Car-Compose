package com.joel.car_compose.ui.cars

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.joel.car_compose.model.Car

@Composable
fun CarDetailedScreen(
    navController: NavHostController
){
    Text(text = "Hi This is Detailed Screen")
}

@Composable
fun DetailsTopAppBar(car: Car){

}
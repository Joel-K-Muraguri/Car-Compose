package com.joel.car_compose.ui.cars

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.joel.car_compose.model.Car


@Composable
fun CarDetailedScreen(
    navController: NavHostController,
//    carId : Int,
//
){
    val car: Car ? = null

    Column() {
        Text(text = "Hey This is the Detailed Screen")
        if (car != null) {
            Text(text = car.model)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DetailedScreenPreview(){
    val navController = rememberNavController()
    CarDetailedScreen(navController)
}
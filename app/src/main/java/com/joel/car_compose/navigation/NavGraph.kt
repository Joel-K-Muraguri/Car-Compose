package com.joel.car_compose.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.joel.car_compose.model.Car
import com.joel.car_compose.ui.cars.CarSharedViewModel
import com.joel.car_compose.utils.Routes

@Composable
fun NavGraph(
    navController: NavHostController,
    carSharedViewModel: CarSharedViewModel,
    context: Context,


){
    NavHost(
        navController = navController,
        startDestination = Routes.AUTHENTICATION_ROUTE ,
        route = Routes.ROOT_ROUTE,
    ){
        authenticationGraph(navController, context)
        contentNavGraph(navController, carSharedViewModel,context)

    }

}
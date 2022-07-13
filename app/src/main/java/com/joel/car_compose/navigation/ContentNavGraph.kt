package com.joel.car_compose.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.joel.car_compose.ui.cars.CarDetailedScreen
import com.joel.car_compose.utils.Routes


fun NavGraphBuilder.contentNavGraph(
    navController: NavHostController
){
    navigation(
        route = Routes.CONTENT_ROUTE,
        startDestination = Routes.CONTENT_SCREEN ,
    ){
        composable(route = Routes.CONTENT_SCREEN){
            ContentScreen()
        }
        composable(route = Routes.DETAILED_SCREEN){
            CarDetailedScreen(navController)
        }
    }

}
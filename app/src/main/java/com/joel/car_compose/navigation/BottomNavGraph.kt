package com.joel.car_compose.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.joel.car_compose.model.Car
import com.joel.car_compose.ui.cars.CarDetailedScreen
import com.joel.car_compose.ui.cars.CarSharedViewModel
import com.joel.car_compose.ui.cars.ListScreen
import com.joel.car_compose.ui.profile.ProfileScreen
import com.joel.car_compose.utils.Routes

@Composable
fun BottomBarNavGraph(
    navController: NavHostController,
    carSharedViewModel: CarSharedViewModel,
    context: Context,
){
    NavHost(
        navController = navController, startDestination = BottomNavRoutes.Home.route )
    {
        composable(route = BottomNavRoutes.Home.route){
            ListScreen(navController,carSharedViewModel, context)
        }
        composable(route = BottomNavRoutes.Profile.route){
            ProfileScreen()
        }

        composable(
            route = Routes.DETAILED_SCREEN,
        ){
            val carModel = navController.previousBackStackEntry?.arguments?.getParcelable<Car>("car")
                CarDetailedScreen(navController, car = carModel)
        }
    }
}

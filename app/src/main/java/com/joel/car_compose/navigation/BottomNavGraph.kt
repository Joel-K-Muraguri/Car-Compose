package com.joel.car_compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.joel.car_compose.ui.cars.CarList
import com.joel.car_compose.ui.cars.ListScreen
import com.joel.car_compose.ui.profile.ProfileScreen

@Composable
fun BottomBarNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController, startDestination = BottomNavRoutes.Home.route )
    {
        composable(route = BottomNavRoutes.Home.route){
            ListScreen(navController)
        }
        composable(route = BottomNavRoutes.Profile.route){
            ProfileScreen()
        }
    }
}
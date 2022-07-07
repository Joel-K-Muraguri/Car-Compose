package com.joel.car_compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.joel.car_compose.utils.Routes

@Composable
fun NavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Routes.AUTHENTICATION_ROUTE ,
        route = Routes.ROOT_ROUTE,
    ){
        authenticationGraph(navController)
        contentNavGraph(navController)

    }

}
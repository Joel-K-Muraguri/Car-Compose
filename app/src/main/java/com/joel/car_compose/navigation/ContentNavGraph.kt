package com.joel.car_compose.navigation

import android.content.Context
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.joel.car_compose.ui.cars.CarDetailedScreen
import com.joel.car_compose.ui.cars.CarSharedViewModel
import com.joel.car_compose.utils.Routes


fun NavGraphBuilder.contentNavGraph(
    navController: NavHostController,
    carSharedViewModel: CarSharedViewModel,
    context: Context
){
    navigation(
        route = Routes.CONTENT_ROUTE,
        startDestination = Routes.CONTENT_SCREEN ,
    ){
        composable(route = Routes.CONTENT_SCREEN){
            ContentScreen(carSharedViewModel, context)
        }

//        composable(
//            route = Routes.DETAILED_SCREEN + "?carId ={carId}",
//            arguments = listOf(
//                navArgument(name = "carId"){
//                    type = NavType.IntType
//                    nullable = true
//
//                }
//            )
//        ){
//           CarDetailedScreen(navController)
//        }
    }

}
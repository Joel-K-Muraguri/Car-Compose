package com.joel.car_compose.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.joel.car_compose.auth.AuthViewModel
import com.joel.car_compose.auth.LoginRequest
import com.joel.car_compose.ui.authentication.auth.AuthenticationScreen
import com.joel.car_compose.ui.authentication.launch.LaunchScreen
import com.joel.car_compose.ui.authentication.login.LogInScreen
import com.joel.car_compose.ui.authentication.signin.SignInScreen
import com.joel.car_compose.utils.Routes


fun NavGraphBuilder.authenticationGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    context: Context
){
    navigation(
        route = Routes.AUTHENTICATION_ROUTE,
        startDestination = Routes.LAUNCH_SCREEN
    ){
        composable(route = Routes.LAUNCH_SCREEN){
            LaunchScreen(navController = navController )
        }

        composable(route = Routes.AUTHENTICATION_SCREEN){
            AuthenticationScreen(navController = navController)
        }
        composable(route = Routes.LOG_IN_SCREEN){
            LogInScreen(navController,authViewModel, context)
        }
        composable(route = Routes.SIGN_IN_SCREEN){
            SignInScreen(navController)
        }
    }
}
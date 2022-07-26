package com.joel.car_compose

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.joel.car_compose.auth.AuthViewModel
import com.joel.car_compose.auth.LoginRequest
import com.joel.car_compose.model.Car
import com.joel.car_compose.navigation.NavGraph
import com.joel.car_compose.ui.cars.CarSharedViewModel
import com.joel.car_compose.ui.theme.CarComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val carSharedViewModel by viewModels<CarSharedViewModel>()
    private lateinit var car: Car
    private val authViewModel by viewModels<AuthViewModel>()
    private lateinit var context : Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context = this
        setContent {
            CarComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    navController = rememberNavController()
                        NavGraph(navController, carSharedViewModel,authViewModel, context)

                }
            }
        }
    }
}


package com.joel.car_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.joel.car_compose.theme.CarComposeTheme
import com.joel.car_compose.ui.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost


class AutoCars : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    DestinationsNavHost(navGraph = NavGraphs.root)

                }
            }
        }
    }
}


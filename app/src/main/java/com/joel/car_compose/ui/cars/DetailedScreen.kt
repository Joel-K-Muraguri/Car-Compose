package com.joel.car_compose.ui.cars

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.joel.car_compose.components.Favourite
import com.joel.car_compose.model.Car
import com.joel.car_compose.utils.Routes
import kotlinx.coroutines.launch


@Composable
fun CarDetailedScreen(
    navController: NavHostController,
    car: Car?

){

    Log.d("TEST::", "goToCarDetails: "+ car?.name)

    Scaffold(
        topBar = {
            DetailsTopAppBar(navController,car)
        },
        content = {
                DetailView()

        }
    )

}

@Composable
fun DetailsTopAppBar(
    navController: NavHostController,
    car: Car?

){
    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = {
                   navController.popBackStack()
                }
            ) {
                Icon(Icons.Filled.ArrowBack,"")
            }
        },

        title = {
           Text(text = "Detailed Screen")

        },
        actions = {
            Favourite()
        }
    )
}

@Composable
fun DetailView(){
    LazyColumn{

    }


}


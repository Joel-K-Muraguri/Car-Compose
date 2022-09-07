package com.joel.car_compose.ui.cars

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joel.car_compose.R
import com.joel.car_compose.components.CarImage
import com.joel.car_compose.components.CarInfo
import com.joel.car_compose.model.data.CarItem
import com.joel.car_compose.ui.destinations.ListScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun CarDetailedScreen(
    navigator: DestinationsNavigator,
    car: CarItem
) {
   Scaffold(
       topBar = {
           TopBar(car,navigator)
       },
       backgroundColor = Color.LightGray,
       content = {
          DetailContent(car)
       }
   )
}

@Composable
fun TopBar(car: CarItem,navigator: DestinationsNavigator){

    TopAppBar{
        val arrowBack = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24)
        IconButton(onClick = {
                    navigator.navigate(ListScreenDestination)
                }) {
                   Image(
                       painter = arrowBack,
                       contentDescription = "arrowBack" )
                }

        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = car.name,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun DetailContent(car: CarItem){
    LazyColumn{
       item {
           CarImage(car)
       }
       item {
           CarInfo(car)
       }
    }
}
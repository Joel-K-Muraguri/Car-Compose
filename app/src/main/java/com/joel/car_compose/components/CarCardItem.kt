package com.joel.car_compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.joel.car_compose.components.Favourite
import com.joel.car_compose.model.Car
import com.joel.car_compose.ui.destinations.CarDetailedScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CarCardItem(
    car: Car,
    navigator: DestinationsNavigator
) {

    val context = LocalContext.current
    Card(
        onClick = {
           navigator.navigate(CarDetailedScreenDestination(car))
        },
        elevation = 5.dp,
        modifier = Modifier
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = White,


    ) {
        Column(
            modifier = Modifier.padding(5.dp)
        ) {
            Row {
                Spacer(modifier = Modifier.height(8.dp))
               Text(
                   text = car.name,
                   style = MaterialTheme.typography.body1,
                   modifier = Modifier
                       .weight(5f)

               )
               Favourite(context,car.id)
            }
           Card(
               modifier = Modifier
                   .fillMaxWidth(),
               backgroundColor = White
           ) {
               Image(
                   painter = rememberAsyncImagePainter(model = car.image),
                   contentDescription = car.name,
                   modifier = Modifier
                       .fillMaxWidth()
                       .height(250.dp)
                       .width(400.dp)
               )
           }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = car.fuel,
                    modifier = Modifier
                        .weight(3f)
                )
                Text(
                    text = car.horsepower,
                    modifier = Modifier
                        .weight(1f)
                )
            }
        }
    }
}


package com.joel.car_compose.ui.cars

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.joel.car_compose.R
import com.joel.car_compose.components.Favourite
import com.joel.car_compose.model.Car

@Composable
fun CarCardItem(
    car: Car,
    modifier: Modifier = Modifier

) {

    Card(
        elevation = 5.dp,
        modifier = Modifier
            .clickable {

            },
        shape = RoundedCornerShape(10.dp),
        backgroundColor = White

    ) {
        Column() {
            Row {
               Text(
                   text = car.name,
                   style = MaterialTheme.typography.body1,
                   modifier = Modifier
                       .weight(5f)

               )
               Favourite()
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
            Row() {

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


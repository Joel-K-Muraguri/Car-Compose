package com.joel.car_compose.ui.cars

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.joel.car_compose.model.Car
import com.joel.car_compose.utils.Routes

@Composable
fun CarCardItem(car: Car){
    val navController = rememberNavController()
    Card(
        elevation = 5.dp,
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                Log.d("TEST::", "CarCardItem: clicked")
                navController.navigate(Routes.DETAILED_SCREEN)
            },
    ) {
        Column() {
            Row(
            ) {
                Column() {
                    Text(
                        text = car.model,
                        modifier = Modifier
                            .weight(4f)
                    )
                    Text(text = car.brand)
                }
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "",
                    modifier = Modifier
                        .weight(1f)
                )
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = car.imageURL),
                    contentDescription = ""
                )
            }

            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = car.brand,
                    modifier = Modifier
                        .weight(2f)
                )
                Text(
                    text = car.model,
                    modifier = Modifier
                        .weight(2f)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = car.fuelType,
                    modifier = Modifier
                        .weight(1f)
                )
            }

        }
    }

}

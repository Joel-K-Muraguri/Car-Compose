package com.joel.car_compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.compose.rememberAsyncImagePainter
import com.joel.car_compose.model.data.CarItem
import com.joel.car_compose.ui.destinations.CarDetailedScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CarCardItem(
    car: CarItem,
    navigator: DestinationsNavigator
) {
    val context = LocalContext.current
    Card(
        onClick = {
           navigator.navigate(CarDetailedScreenDestination)
        },
        elevation = 5.dp,
        shape = RoundedCornerShape(20.dp),
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
                   .fillMaxSize()
                   .fillMaxWidth()
                   .height(250.dp),
           ) {
               SubcomposeAsyncImage(
                   model = car.image,
                   contentDescription = "bmw x5",
                   modifier = Modifier
                       .fillMaxWidth()
               ) {
                   val state = painter.state
                   if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                       CircularProgressIndicator(
                           modifier = Modifier
                               .scale(0.5f)
                       )
                   } else {
                       SubcomposeAsyncImageContent()
                   }
               }
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


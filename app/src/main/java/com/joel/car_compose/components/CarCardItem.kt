package com.joel.car_compose.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.joel.car_compose.model.data.CarItem
import com.joel.car_compose.theme.Purple700
import com.joel.car_compose.ui.destinations.CarDetailedScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CarCardItem(
    car: CarItem,
    navigator: DestinationsNavigator
) {

    Card(
        onClick = {
                  navigator.navigate(CarDetailedScreenDestination(car))
        },
        elevation = 5.dp,
        shape = RoundedCornerShape(20.dp),
    ) {

        Column(
            modifier = Modifier
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                contentAlignment = Alignment.Center
            ) {
                SubcomposeAsyncImage(
                    model = car.image,
                    contentDescription = "bmw x5",
                    modifier = Modifier
                        .fillMaxSize()
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


            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = car.name,
                style = MaterialTheme.typography.h5,
                color = Black,
                textAlign = TextAlign.Center

            )

            Text(
                text = "Starts from Ksh${car.price}",
                color = LightGray,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(5.dp))
            Box(
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Purple700,
                        contentColor = White
                    ),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Add to WishList",
                        fontFamily = FontFamily.SansSerif,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

    }
}


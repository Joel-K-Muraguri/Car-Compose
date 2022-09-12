package com.joel.car_compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.joel.car_compose.R
import com.joel.car_compose.model.data.CarItem

@Composable
fun CarImage( car: CarItem){

    val carImage = painterResource(id = R.drawable.img_porsche_cayenne)
    Image(
        painter = carImage,
        contentDescription = car.name,
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp),
        contentScale = ContentScale.FillWidth
    )
}

@Composable
fun LogoImage(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.img_cartoon_logo),
                contentDescription = "Cartoon_Logo",
                modifier = Modifier

                    .height(200.dp),

                )
        }
    }
}
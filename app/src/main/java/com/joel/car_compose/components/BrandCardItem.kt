package com.joel.car_compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.joel.car_compose.model.Brand


@Composable
fun BrandCardItem(
    brand: Brand,

){

    Column(
        modifier = Modifier
            .height(150.dp)
            .width(100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Card(
            modifier = Modifier
                .clickable {

                }
                .padding(8.dp)
                .height(50.dp)
                .width(50.dp),
            shape = RoundedCornerShape(10.dp),
            backgroundColor = Color.LightGray,
            elevation = 5.dp,
        ) {

           Image(
               painter = rememberAsyncImagePainter(brand.logo),
               contentDescription = brand.name,
               modifier = Modifier.size(128.dp)
           )

        }
        Text(
            text = brand.name,
        modifier = Modifier
            .padding(8.dp)
            )
    }


}
package com.joel.car_compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CarInfoCard(){
    Column(
        modifier = Modifier
            .padding(20.dp)
    ) {
        Text(
            text = "Porsche Cayenne",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
        ) //Name
        Spacer(modifier = Modifier.height(20.dp))
        CarSpecs()

    }
}

@Composable
fun CarSpecs(){
   Row(
       horizontalArrangement = Arrangement.SpaceBetween
   ) {
       Box(modifier = Modifier
           .background(color = Color.LightGray)
           )
       {
           Column() {
               Text(
                   text = "Engine Capacity",
                   fontSize = 8.sp
               )
               Spacer(modifier = Modifier.height(5.dp))
               Text(text = "1500cc")
           }
       }


       Box(modifier = Modifier
           .background(color = Color.Blue)

           ) {
           Column() {
               Text(
                   text = "Transmission",
                   fontSize = 8.sp,

                   )
               Spacer(modifier = Modifier.height(5.dp))
               Text(text = "Automatic")
           }
       }

   }
}


@Preview(showBackground = true)
@Composable
fun CarInfoCardPreview(){
    CarInfoCard()
}

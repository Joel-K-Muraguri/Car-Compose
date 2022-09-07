package com.joel.car_compose.ui.authentication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joel.car_compose.R
import com.joel.car_compose.ui.destinations.LogInScreenDestination
import com.joel.car_compose.ui.destinations.SignInScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination
@Composable
fun AuthenticationScreen(
    navigator: DestinationsNavigator,

){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(all = 20.dp)
            .fillMaxSize()
            .fillMaxHeight()
    ) {
        Text(
            text = "AUTO CARS",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Blue,
            fontFamily = FontFamily.Cursive
        )
        Spacer(modifier = Modifier.height(50.dp))
        BusinessImageLogo()
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = {
                 navigator.navigate(LogInScreenDestination)
            },
            modifier = Modifier
                .width(200.dp)
            ,
        ) {
            Text(
                text = "LOG IN",
                modifier = Modifier.height(20.dp)
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Button(
            onClick = {
                      navigator.navigate(SignInScreenDestination)
            },
            modifier = Modifier
                .width(200.dp)
            ,
        ) {
            Text(
                text = "SIGN IN",
                modifier = Modifier.height(20.dp)
            )
        }
    }
}

@Composable
fun BusinessImageLogo(){
    Card(
        modifier = Modifier
            .height(200.dp)
            .width(200.dp),
        elevation = 5.dp,
        backgroundColor = Color.Black,
        border = BorderStroke(5.dp, color = Color.Black)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_cartoon_logo),
            contentDescription = "Cartoon_Logo",
            modifier = Modifier
                .height(200.dp),
            )

    }
}

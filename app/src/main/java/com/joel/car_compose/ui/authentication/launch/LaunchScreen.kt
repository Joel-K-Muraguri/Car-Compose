package com.joel.car_compose.ui.authentication.launch

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.joel.car_compose.R
import com.joel.car_compose.utils.Routes

@Composable
fun LaunchScreen(
    navController: NavHostController
){
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(all = 20.dp)
            .fillMaxSize()
            .fillMaxHeight(),
    ) {
        
        Text(
            text = "AUTO CARS",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Blue,
            fontFamily = FontFamily.Cursive
        )
        Spacer(modifier = Modifier.height(30.dp))
        BusinessLogo()
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = { navController.navigate(route = Routes.CONTENT_SCREEN) },
            modifier = Modifier
                .width(200.dp),
            shape = CircleShape
                
        ) {
            Text(
                text = "Get Started",
                modifier = Modifier.height(20.dp)
            )
        }
        
    }
}


@Composable
fun BusinessLogo(){
    Card(
        modifier = Modifier
            .height(200.dp)
            .width(200.dp),
        elevation = 5.dp,
        backgroundColor = Color.Black
    ) {
       Image(
           painter = painterResource(id = R.drawable.img),
           contentDescription = "businnes logo" )
    }
}

@Preview(showBackground = true)
@Composable
fun LaunchScreenPreview(){
    val navController = rememberNavController()
    LaunchScreen(navController = navController)
}
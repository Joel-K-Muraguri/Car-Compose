package com.joel.car_compose.ui.authentication.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.joel.car_compose.utils.Routes

@Composable
fun LogInScreen(
    navController: NavHostController
){
    var userName by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxSize()
    ) {
        Text(
            text = "AUTO CARS",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Blue,
            fontFamily = FontFamily.Cursive
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = userName,
            onValueChange = {userName = it},
            label = {
                Text(text = "UserName/ Email")
            },
            shape = CircleShape
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = {
                Text(text = "Password")
            },
            shape = CircleShape
        )
        Spacer(modifier = Modifier.height(5.dp))
        Button(onClick = { navController.navigate(route = Routes.CONTENT_SCREEN) }) {
           Text(text = "LOG IN")
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Forgot Password"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LogInScreenPreview(){
    val navController = rememberNavController()
    LogInScreen(navController)
}
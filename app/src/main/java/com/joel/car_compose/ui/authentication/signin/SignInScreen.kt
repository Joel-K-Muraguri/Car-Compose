package com.joel.car_compose.ui.authentication.signin

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
import com.joel.car_compose.ui.authentication.auth.BusinessImageLogo
import com.joel.car_compose.utils.Routes

@Composable
fun SignInScreen(
    navController: NavHostController
){
    var userName by remember {
        mutableStateOf("")
    }
    var name by remember {
        mutableStateOf("")
    }
    var email by remember {
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

       OutlinedTextField(
           value = userName,
           onValueChange = {userName = it},
           label = {
               Text(text = "UserName")
           },
           shape = CircleShape
       )
       Spacer(modifier = Modifier.height(5.dp))
       OutlinedTextField(
           value = name,
           onValueChange = {name= it},
           label = {
               Text(text = "Name")
           },
           shape = CircleShape
       )
       Spacer(modifier = Modifier.height(5.dp))
       OutlinedTextField(
           value = email,
           onValueChange = {email = it},
           label = {
               Text(text = " Email")
           },
           shape = CircleShape
       )
       Spacer(modifier = Modifier.height(5.dp))
       OutlinedTextField(
           value = password,
           onValueChange = {password = it},
           label = {
               Text(text = "Enter Password")
           },
           shape = CircleShape
       )
       Spacer(modifier = Modifier.height(5.dp))
       OutlinedTextField(
           value = password,
           onValueChange = {password = it},
           label = {
               Text(text = "Confirm Password")
           },
           shape = CircleShape
       )
       Spacer(modifier = Modifier.height(5.dp))
       Button(onClick = { navController.navigate(route = Routes.CONTENT_SCREEN) }) {
           Text(text = "SIGN IN")
       }
   }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview(){
    val navController = rememberNavController()
    SignInScreen(navController)
}
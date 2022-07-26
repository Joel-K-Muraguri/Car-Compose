package com.joel.car_compose.ui.authentication.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
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
import com.joel.car_compose.auth.AuthViewModel
import com.joel.car_compose.auth.LoginRequest
import com.joel.car_compose.auth.SessionManager
import com.joel.car_compose.auth.TokenResponse
import com.joel.car_compose.network.ApiService
import com.joel.car_compose.utils.Routes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun LogInScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    context: Context
){
    var userName by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var text by remember { mutableStateOf("Log In") }

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
            shape = RoundedCornerShape(10.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = {
                Text(text = "Password")
            },
            shape = RoundedCornerShape(10.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Button(onClick = {
//            text = "Loading...."
            loginUser(context,LoginRequest(userName,password),navController)

        }) {
           Text(text)
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Forgot Password?",
            color = Color.Blue,


        )
    }
}

fun loginUser(context: Context,loginRequest: LoginRequest, navController: NavHostController){
    val apiService = ApiService.getInstance()
    val sessionManager = SessionManager(context)
        Toast.makeText(context, "Logging in...", Toast.LENGTH_LONG).show()
        apiService
            .login(loginRequest).enqueue(object : Callback<TokenResponse>{
                override fun onResponse(
                    call: Call<TokenResponse>,
                    response: Response<TokenResponse>,
                ) {
                    if (response.code() == 200 && response.body() != null){
                        //This means we have successfully logged in
                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                        val userData = response.body()
                        sessionManager.saveAuthToken(userData!!.token)
                        navController.navigate(Routes.CONTENT_SCREEN)
                    }
                    else if (response.code() == 401){
                        Log.d("TEST::", "onResponse: "+response.message())
                        TODO("Show Toast or alert saying invalid credentials")
                    }
                    else{
                        Log.d("TEST::", "onResponse: "+response.message())
                        TODO("Show Toast saying: Something went wrong, please try again later.")
                    }
                }

                override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                    Log.d("TEST::", "onResponse: "+t.message)
                    TODO("Show toast saying: Please check internet connection ")
                }

            })

}

@Preview(showBackground = true)
@Composable
fun LogInScreenPreview(){
    val navController = rememberNavController()
//    LogInScreen(navController)
}
package com.joel.car_compose.ui.authentication

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.joel.car_compose.model.auth.LoginRequest
import com.joel.car_compose.model.auth.SessionManager
import com.joel.car_compose.model.auth.TokenResponse
import com.joel.car_compose.model.network.ApiService
import com.joel.car_compose.R
import com.joel.car_compose.ui.destinations.ListScreenDestination
import com.joel.car_compose.ui.destinations.SignInScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Destination
@Composable
fun LogInScreen(
    navigator: DestinationsNavigator,

){
    val context = LocalContext.current
    var userName by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    val isFormValid by derivedStateOf {
        userName.isNotBlank() && password.length >= 8
    }

    Scaffold(
        backgroundColor = Color.Blue
    ) {
       Column(
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Top,
           modifier = Modifier.fillMaxSize()
       ) {

           Image(
               painter = painterResource(id = R.drawable.img_cartoon_logo),
               contentDescription = "Cartoon_Logo",
               modifier = Modifier
                   .weight(1f)
                   .height(200.dp),

           )

           Card(
               modifier = Modifier
                   .padding(10.dp)
                   .weight(2f),
               shape = RoundedCornerShape(32.dp)
           ) {

               Column(
                   modifier = Modifier
                       .padding(32.dp)
                       .fillMaxSize(),
               ) {
                   Text(
                       text = "Welcome Back!",
                       fontSize = 32.sp,
                       fontWeight = FontWeight.ExtraBold
                   )
                   Spacer(modifier = Modifier.height(20.dp))
                   Spacer(modifier = Modifier.weight(1f))
                   Column(
                       verticalArrangement = Arrangement.Center,
                       horizontalAlignment = Alignment.CenterHorizontally,
                       modifier = Modifier.fillMaxWidth()
                   ) {
                       OutlinedTextField(
                           value = userName,
                           onValueChange = {
                               userName = it
                           },
                           singleLine = true,
                           trailingIcon = {
                               if (userName.isNotBlank()){
                                   IconButton(onClick = { userName = "" }) {
                                       Icon(
                                           imageVector = Icons.Filled.Clear,
                                           contentDescription = "Clear")
                                   }
                               }
                           },
                           label = {
                               Text(text = "UserName")
                           },
                           modifier = Modifier.fillMaxWidth(),
                       )

                       Spacer(modifier = Modifier.height(8.dp))
                       OutlinedTextField(

                           value = password,
                           onValueChange ={
                               password = it
                           },
                           modifier = Modifier.fillMaxWidth(),
                           singleLine = true,
                           label = { Text(text = "Password")},
                           keyboardOptions = KeyboardOptions(
                               keyboardType = KeyboardType.Password,
                               imeAction = ImeAction.Done),
                           visualTransformation = if(isPasswordVisible)
                               VisualTransformation.None
                           else
                               PasswordVisualTransformation(),
                           trailingIcon = {
                               val image = if (isPasswordVisible)
                                   Icons.Filled.Visibility
                               else Icons.Filled.VisibilityOff
                               val description = if (isPasswordVisible) "Hide password" else "Show password"

                               IconButton(onClick = { isPasswordVisible =! isPasswordVisible }) {
                                   Icon(
                                       imageVector = image,
                                       contentDescription = description
                                   )
                               }
                           }
                       )
                       Spacer(modifier = Modifier.height(8.dp))
                       Box(contentAlignment = Alignment.TopStart) {
                           Text(
                               text = "Password should be at least 8 characters",
                               style = MaterialTheme.typography.caption,
                               textAlign = TextAlign.Start
                           )
                       }
                       Spacer(modifier = Modifier.height(16.dp))
                       Button(
                           onClick = { loginUser(context, LoginRequest(userName,password),navigator) },
                           enabled = isFormValid,
                           modifier = Modifier.fillMaxWidth()
                       ) {
                           Text(text = "Log in")
                       }
                       Spacer(modifier = Modifier.weight(1f))
                       Row(
                           modifier = Modifier.fillMaxWidth(),
                           horizontalArrangement = Arrangement.SpaceBetween
                       ) {
                           TextButton(onClick = { /*TODO*/ }) {
                               Text(
                                   text = "Forgot Password?",
                                   color = Color.Blue,
                                   style = MaterialTheme.typography.caption
                               )
                           }
                           TextButton(onClick = { navigator.navigate(SignInScreenDestination) }) {
                               Text(
                                   text = "Sign In",
                                   color = Color.Blue,
                                   style = MaterialTheme.typography.h6
                               )
                           }
                       }
                   }
               }

           }
       }
    }
}



fun loginUser(context: Context,loginRequest: LoginRequest, navigator: DestinationsNavigator){
    val apiService = ApiService.getInstance()
    val sessionManager = SessionManager(context)
        Toast.makeText(context, "Logging in...", Toast.LENGTH_LONG).show()
        apiService
//            .toggleFavoriteCar("Token ${sessionManager.fetchAuthToken()}", car.id)
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
                        navigator.navigate(ListScreenDestination)
                    }
                    else if (response.code() == 401){
                        Toast.makeText(context, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                        Log.d("TEST::", "onResponse: "+response.message())

                    }
                    else{
                        Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show()
                        Log.d("TEST::", "onResponse: "+response.message())

                    }
                }

                override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                    Toast.makeText(context, "Please Check Internet Connection", Toast.LENGTH_SHORT).show()
                    Log.d("TEST::", "onResponse: "+t.message)

                }

            })

}

@Preview(showBackground = true)
@Composable
fun LogInScreenPreview(){
    val navController = rememberNavController()
    val context = rememberCompositionContext()

}
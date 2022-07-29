package com.joel.car_compose.ui.authentication.login

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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.joel.car_compose.R
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
    context: Context
){
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
                       Spacer(modifier = Modifier.height(16.dp))
                       Button(
                           onClick = { loginUser(context, LoginRequest(userName,password),navController) },
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
                           TextButton(onClick = { navController.navigate(Routes.SIGN_IN_SCREEN) }) {
                               Text(
                                   text = "Sign In",
                                   color = Color.Blue
                               )
                           }
                           TextButton(onClick = { /*TODO*/ }) {
                               Text(
                                   text = "Forgot Password?",
                                   color = Color.Gray
                               )
                           }
                       }
                   }
               }

           }
       }
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
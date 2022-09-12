package com.joel.car_compose.ui.authentication

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.joel.car_compose.R
import com.joel.car_compose.model.auth.AuthResult
import com.joel.car_compose.model.auth.AuthUiEvent
import com.joel.car_compose.model.auth.AuthViewModel
import com.joel.car_compose.ui.destinations.ListScreenDestination
import com.joel.car_compose.ui.destinations.SignInScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo

@Destination
@Composable
fun LogInScreen(
    navigator: DestinationsNavigator,
    authViewModel: AuthViewModel = hiltViewModel(),
    ){


    var isPasswordVisible by remember {
        mutableStateOf(false)
    }
    val state = authViewModel.state
    val context = LocalContext.current

    LaunchedEffect(authViewModel,context){
        authViewModel.authResults.collect{ result ->
            when(result){
                is AuthResult.Authorized -> {
                    Toast.makeText(context, "Successful ", Toast.LENGTH_SHORT).show()
                    navigator.navigate(ListScreenDestination){
                        popUpTo(ListScreenDestination){
                            inclusive = true
                        }
                    }
                }

                is AuthResult.UnAuthorized -> {
                    Toast.makeText(context, "Unauthorized ", Toast.LENGTH_SHORT).show()

                }

                is AuthResult.UnknownError -> {
                    Toast.makeText(context, "Unknown Error ", Toast.LENGTH_SHORT).show()
                }
            }

        }

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
                           value = state.isUserNameChangedLogIn,
                           onValueChange = {
                               authViewModel.onEvents(AuthUiEvent.IsUserNameLogInChanged(it))
                           },
                           singleLine = true,

                           label = {
                               Text(text = "UserName")
                           },
                           modifier = Modifier.fillMaxWidth(),
                       )

                       Spacer(modifier = Modifier.height(8.dp))
                       OutlinedTextField(

                           value = state.isPasswordChangedLogIn,
                           onValueChange ={
                               authViewModel.onEvents(AuthUiEvent.IsPasswordLogInChanged(it))
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
                           onClick = {
                                     authViewModel.onEvents(AuthUiEvent.LogIn)
                           },
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

    if (state.isLoading){
        Box(
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    }
}

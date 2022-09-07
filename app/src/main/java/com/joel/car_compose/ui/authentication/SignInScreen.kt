package com.joel.car_compose.ui.authentication

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joel.car_compose.model.auth.RegisterRequest
import com.joel.car_compose.model.auth.SessionManager
import com.joel.car_compose.model.auth.TokenResponse
import com.joel.car_compose.model.network.ApiService
import com.joel.car_compose.components.LogoImage
import com.joel.car_compose.ui.destinations.ListScreenDestination
import com.joel.car_compose.ui.destinations.LogInScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Destination
@Composable
fun SignInScreen(
    navigator: DestinationsNavigator,

){

    Scaffold(
        backgroundColor = Color.Blue,
        content = {
            SignInScreenContent(navigator)
        }
    )
}

@Composable
fun SignInScreenContent(navigator: DestinationsNavigator) {
    var userName by remember {
        mutableStateOf("")
    }
    var phoneNumber by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var location by remember {
        mutableStateOf("")
    }
    var newPassword by remember {
        mutableStateOf("")
    }
    newPassword >= 8.toString()
    var confirmPassword by remember {
        mutableStateOf("")
    }
    confirmPassword == newPassword

    val isPasswordValid by derivedStateOf {
        newPassword == confirmPassword
    }
    val isFormValid by derivedStateOf {
        userName.isNotBlank() && email.isNotBlank() && location.isNotBlank() && phoneNumber.isNotBlank() && newPassword.isNotBlank() && confirmPassword.isNotBlank() && isPasswordValid
    }
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    LazyColumn {
        item {
            LogoImage()
        }
        item {
            Card(
                modifier = Modifier
                    .padding(10.dp),
                shape = RoundedCornerShape(32.dp)

            ) {

                Column(
                    modifier = Modifier
                        .padding(32.dp)
                        .fillMaxSize(),
                ) {
                    Text(
                        text = "Welcome To Auto Cars",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
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
                                if (userName.isNotBlank()) {
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
                        Spacer(modifier = Modifier.height(2.dp))
                        OutlinedTextField(
                            value = phoneNumber,
                            onValueChange = {
                                phoneNumber = it
                            },
                            singleLine = true,
                            trailingIcon = {
                                if (phoneNumber.isNotBlank()) {
                                    IconButton(onClick = { phoneNumber = "" }) {
                                        Icon(
                                            imageVector = Icons.Filled.Clear,
                                            contentDescription = "Clear")
                                    }
                                }
                            },
                            label = {
                                Text(text = "Phone Number")
                            },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                            ),
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        OutlinedTextField(
                            value = email,
                            onValueChange = {
                                email = it
                            },
                            singleLine = true,
                            trailingIcon = {
                                if (email.isNotBlank()) {
                                    IconButton(onClick = { email = "" }) {
                                        Icon(
                                            imageVector = Icons.Filled.Clear,
                                            contentDescription = "Clear")
                                    }
                                }
                            },
                            label = {
                                Text(text = "Email")
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        OutlinedTextField(
                            value = location,
                            onValueChange = {
                                location = it
                            },
                            singleLine = true,
                            trailingIcon = {
                                if (location.isNotBlank()) {
                                    IconButton(onClick = { location = "" }) {
                                        Icon(
                                            imageVector = Icons.Filled.Clear,
                                            contentDescription = "Clear")
                                    }
                                }
                            },
                            label = {
                                Text(text = "Location")
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )

                        Spacer(modifier = Modifier.height(2.dp))
                        OutlinedTextField(
                            value = newPassword,
                            onValueChange = {
                                newPassword = it
                            },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            label = { Text(text = "New Password") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done),
                            visualTransformation = if (isPasswordVisible)
                                VisualTransformation.None
                            else
                                PasswordVisualTransformation(),
                            trailingIcon = {
                                val image = if (isPasswordVisible)
                                    Icons.Filled.Visibility
                                else Icons.Filled.VisibilityOff
                                val description =
                                    if (isPasswordVisible) "Hide password" else "Show password"

                                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                                    Icon(
                                        imageVector = image,
                                        contentDescription = description
                                    )

                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Box(contentAlignment = Alignment.TopStart) {
                            Text(
                                text = "Password should be at least 8 characters",
                                style = MaterialTheme.typography.caption,
                                textAlign = TextAlign.Start
                            )
                        }

                        Spacer(modifier = Modifier.height(2.dp))
                        OutlinedTextField(
                            value = confirmPassword,
                            onValueChange = {
                                confirmPassword = it
                            },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            label = { Text(text = "Confirm Password") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done),
                            visualTransformation = if (isPasswordVisible)
                                VisualTransformation.None
                            else
                                PasswordVisualTransformation(),
                            trailingIcon = {
                                val image = if (isPasswordVisible)
                                    Icons.Filled.Visibility
                                else Icons.Filled.VisibilityOff
                                val description =
                                    if (isPasswordVisible) "Hide password" else "Show password"

                                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                                    Icon(
                                        imageVector = image,
                                        contentDescription = description
                                    )

                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Box(contentAlignment = Alignment.TopStart) {
                            Text(
                                text = "Password should be at least 8 characters",
                                style = MaterialTheme.typography.caption,
                                textAlign = TextAlign.Start
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(
                            onClick = {
                                register(
                                    context,
                                    RegisterRequest(location,
                                        email,
                                        phoneNumber,
                                        userName,
                                        confirmPassword),
                                    navigator,
                                )
                            },
                            enabled = isFormValid,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Sign in")
                        }

                        TextButton(onClick = { navigator.navigate(LogInScreenDestination) }) {
                            Text(
                                text = "Already have an Account?",
                                color = Color.Blue
                            )

                        }
                    }
                }

            }
        }
}
}

fun register(
    context: Context,
    registerRequest: RegisterRequest,
    navigator: DestinationsNavigator,

    ) {
    val apiService = ApiService.getInstance()
    val sessionManager = SessionManager(context)

    Toast.makeText(context, "Signing in...", Toast.LENGTH_SHORT).show()

    apiService.register(registerRequest).enqueue(object : Callback<TokenResponse> {
        override fun onResponse(
            call: Call<TokenResponse>,
            response: Response<TokenResponse>
        ) {
            if (response.code() == 200 && response.body() != null) {
                //Successful Registration
                Toast.makeText(context, "Successful", Toast.LENGTH_SHORT).show()
                val userData = response.body()
                sessionManager.saveAuthToken(userData!!.token)
                navigator.navigate(ListScreenDestination)
            } else if (response.code() == 401) {
                Log.d("TEST::", "onResponse: " + response.message())
                //Already existing credentials
                Toast.makeText(context, "Existing Credentials", Toast.LENGTH_SHORT).show()
            } else if (response.code() == 403) {
                Toast.makeText(context, "Forbidden", Toast.LENGTH_SHORT).show()
            } else
            //Something went wrong
                Log.d("TEST::", "onResponse: " + response.message())
            Toast.makeText(context, "Something went Wrong", Toast.LENGTH_SHORT).show()

        }

        override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
            Log.d("TEST::", "onResponse: " + t.message)
            Toast.makeText(context, "Check your Internet Connection", Toast.LENGTH_SHORT)
                .show()
        }
    })
}




//@Preview(showBackground = true)
//@Composable
//fun SignInScreenPreview(){
//    val navController = rememberNavController()
//    SignInScreen(navController)
//}
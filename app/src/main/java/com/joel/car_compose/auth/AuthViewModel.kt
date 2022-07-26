package com.joel.car_compose.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joel.car_compose.network.ApiService
import kotlinx.coroutines.launch


class AuthViewModel() : ViewModel() {

    private var logInResponse : MutableLiveData<TokenResponse> = MutableLiveData()
    val errorMessages : String by mutableStateOf("An Unknown Error Occurred")

    fun logIn(
       loginRequest: LoginRequest
   ){
       viewModelScope.launch {
           val apiService = ApiService.getInstance()
//           try {
//               val login = apiService.login(loginRequest)
//               logInResponse.value = login
//           }
//           catch (e : Exception){
//               e.message.toString()
//           }
       }
   }

}
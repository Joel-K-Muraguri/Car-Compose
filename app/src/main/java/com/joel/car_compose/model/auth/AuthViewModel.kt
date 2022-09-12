package com.joel.car_compose.model.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository : AuthRepo
) : ViewModel() {

    var state by mutableStateOf(AuthState())

    private val resultChannel = Channel<AuthResult<Unit>>()
    var authResults = resultChannel.receiveAsFlow()

    init {
        authenticate()
    }

    fun onEvents(event: AuthUiEvent){
        when(event){
            is AuthUiEvent.IsPasswordLogInChanged -> {
                state = state.copy(isPasswordChangedLogIn = event.password)
            }

            is AuthUiEvent.IsUserNameLogInChanged -> {
                state = state.copy(isUserNameChangedLogIn = event.userName)

            }

            is AuthUiEvent.LogIn -> {
                login()

            }

            is AuthUiEvent.IsUserNameSignInChanged -> {
                state = state.copy(isUserNameChangedSignIn = event.userName)

            }

            is AuthUiEvent.IsPhoneNumberSignInChanged -> {
                state = state.copy(isPhoneNumberChangedSignIn = event.phoneNumber)

            }

            is AuthUiEvent.IsEmailSignInChanged -> {
                state = state.copy(isEmailChangedSignIn = event.email)

            }

            is AuthUiEvent.IsLocationSignInChanged -> {
                state = state.copy(isLocationChangedSignIn = event.location)

            }

            is AuthUiEvent.IsPasswordSignInChanged -> {
                state = state.copy(isPasswordChangedSignIn = event.password)


            }

            is AuthUiEvent.SignIn -> {
                register()

            }
        }
    }

    private fun register(){
        viewModelScope.launch {
            state = state.copy(isLoading = true)
           val result = repository.register(
               userName = state.isUserNameChangedSignIn,
               password = state.isPasswordChangedSignIn,
               email = state.isEmailChangedSignIn,
               location = state.isLocationChangedSignIn,
               phoneNumber = state.isPhoneNumberChangedSignIn
           )
            resultChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }

    private fun login(){
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = repository.login(
                userName = state.isUserNameChangedLogIn,
                password = state.isPasswordChangedLogIn,
            )
            resultChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }

    private fun authenticate(){
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = repository.authenticate()
            resultChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }





}
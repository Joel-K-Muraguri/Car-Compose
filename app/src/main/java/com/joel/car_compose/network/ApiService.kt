package com.joel.car_compose.network

import com.joel.car_compose.auth.LoginRequest
import com.joel.car_compose.auth.RegisterRequest
import com.joel.car_compose.auth.TokenResponse
import com.joel.car_compose.model.Car
import com.joel.car_compose.model.Brand
import com.joel.car_compose.utils.ApiConstants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {

    @POST(ApiConstants.REGISTER_ENDPOINT)
    fun register (@Body request: RegisterRequest) : Call<TokenResponse>

    @POST(ApiConstants.LOG_IN_ENDPOINT)
    fun login(@Body request: LoginRequest) : Call<TokenResponse>

    @GET(ApiConstants.CAR_LIST_ENDPOINT)
    suspend fun getAutoList() : List<Car>

    @GET(ApiConstants.CAR_BRAND_ENDPOINT)
    suspend fun getBrandList() : List<Brand>

    @GET(ApiConstants.FAVOURITES_ENDPOINT)
    suspend fun getFavourites(
        @Header ("Authorization") token : String
    )

    companion object{
        private var apiService : ApiService ?= null
        fun getInstance () : ApiService{
            if (apiService == null){
                apiService = Retrofit.Builder()
                    .baseUrl(ApiConstants.MAIN_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }

}
package com.joel.car_compose.model.network

import com.joel.car_compose.model.auth.LoginRequest
import com.joel.car_compose.model.auth.RegisterRequest
import com.joel.car_compose.model.auth.TokenResponse
import com.joel.car_compose.model.data.Brand
import com.joel.car_compose.model.data.CarItem
import com.joel.car_compose.model.fav.FavouriteCarItem
import com.joel.car_compose.model.fav.FavouriteResponseItem
import com.joel.car_compose.utils.ApiConstants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {

    @POST(ApiConstants.REGISTER_ENDPOINT)
    fun register (@Body request: RegisterRequest) : TokenResponse

    @POST(ApiConstants.LOG_IN_ENDPOINT)
    fun login(@Body request: LoginRequest) : TokenResponse

    @POST(ApiConstants.AUTHENTICATE)
    fun authenticate(
        @Header("Authorization") token : String
    ) : TokenResponse

    @GET(ApiConstants.CAR_LIST_ENDPOINT)
    suspend fun getCarList() : List<CarItem>

    @GET(ApiConstants.CAR_BRAND_ENDPOINT)
    suspend fun getBrandList() : List<Brand>

    @GET(ApiConstants.FAVOURITES_ENDPOINT)
    suspend fun fetchFavourites(
        @Header ("Authorization") token : String
    ) : List<FavouriteResponseItem>

    @GET(ApiConstants.FAVOURITE_CAR_ID)
     fun toggleFavoriteCar(
        @Header ("Authorization") token : String,
        @Path ("car_id") carId: Int
    ) : Call<FavouriteCarItem>


     @GET(ApiConstants.CAR_DETAILS)
    fun fetchCarDetails(
         @Path("id") id: Int
    ) : Call<CarItem>


    companion object{
        private var apiService : ApiService?= null
        fun getInstance () : ApiService {
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
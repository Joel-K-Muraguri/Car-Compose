package com.joel.car_compose.model.network

import com.joel.car_compose.model.auth.LogInRequest
import com.joel.car_compose.model.auth.RegisterRequest
import com.joel.car_compose.model.auth.TokenResponse
import com.joel.car_compose.model.data.BrandItem
import com.joel.car_compose.model.data.CarItem
import com.joel.car_compose.utils.ApiConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {

    @POST(ApiConstants.REGISTER_ENDPOINT)
    suspend fun register(
        @Body request: RegisterRequest
    ) : TokenResponse


    @POST(ApiConstants.LOG_IN_ENDPOINT)
    suspend fun login(
        @Body request: LogInRequest
    ) : TokenResponse

    @GET(ApiConstants.AUTHENTICATE_ENDPOINT)
    suspend fun authenticate(
        @Header("Authorization") token : String
    ) : TokenResponse

    @GET(ApiConstants.CAR_DETAILS)
    suspend fun getCarInfo(
        @Path("id") id: Int
    ) : CarItem

    @GET(ApiConstants.CAR_LIST_ENDPOINT)
    suspend fun getCarList() : List<CarItem>

    @GET(ApiConstants.CAR_BRAND_ENDPOINT)
    suspend fun getBrandList() : List<BrandItem>

    @GET(ApiConstants.FAVOURITES_ENDPOINT)
    suspend fun getFavourites(
        @Header ("Authorization") token: String
    )

    @GET(ApiConstants.FAVOURITE_CAR_ID)
    suspend fun toggleFavourite(
        @Header ( "Authorization") token: String,
        @Path("id") id: Int
    )



    companion object {
        var apiService: ApiService? = null
        fun getInstance() : ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(ApiConstants.MAIN_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }



}
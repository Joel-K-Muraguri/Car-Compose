package com.joel.car_compose.di

import com.joel.car_compose.network.ApiService
import com.joel.car_compose.network.CarRepository
import com.joel.car_compose.utils.ApiConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

   @Provides
   @Singleton
   fun providesAutoList () : ApiService{
       return Retrofit.Builder()
           .baseUrl(ApiConstants.MAIN_URL)
           .addConverterFactory(GsonConverterFactory.create())
           .build()
           .create(ApiService::class.java)
   }

    @Provides
    @Singleton
    fun providesBrandList () : ApiService{
        return Retrofit.Builder()
            .baseUrl(ApiConstants.MAIN_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    }

    @Provides
    @Singleton
    fun providesCarRepository(api: ApiService) = CarRepository(api)


}
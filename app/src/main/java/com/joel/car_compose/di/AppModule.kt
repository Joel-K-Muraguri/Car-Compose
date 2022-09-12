package com.joel.car_compose.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.joel.car_compose.model.auth.AuthRepo
import com.joel.car_compose.model.auth.AuthRepoImplemention
import com.joel.car_compose.model.network.ApiService
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
    fun providesRetrofit() : ApiService {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.MAIN_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesSharedPreference(application: Application) : SharedPreferences{
        return application.getSharedPreferences("prefs", MODE_PRIVATE)
    }


    @Provides
    @Singleton
    fun provideAuthRepository(apiService: ApiService, prefs: SharedPreferences): AuthRepo {
        return AuthRepoImplemention(apiService, prefs)
    }
}
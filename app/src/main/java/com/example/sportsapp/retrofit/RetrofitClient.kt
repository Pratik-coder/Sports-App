package com.example.sportsapp.retrofit

import com.example.sportsapp.constant.Constants
import com.example.sportsapp.interceptor.SportsInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun getRetrofitInstance(): Retrofit {
        var retrofit=Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                .addInterceptor(SportsInterceptor())
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build())
            .build()

        return retrofit
    }
}
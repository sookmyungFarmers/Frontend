package com.example.haeun.com.haeun.sprinkler1.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder

object RetrofitClient {

    private const val BASE_URL = "http://192.168.56.1:8080/"

    val apiService: ProblemApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProblemApiService::class.java)
    }
}

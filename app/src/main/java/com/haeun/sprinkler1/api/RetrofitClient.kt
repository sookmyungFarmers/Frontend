package com.example.haeun.com.haeun.sprinkler1.api

import com.example.haeun.com.haeun.sprinkler1.api.LoginApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object RetrofitClient {
    //private const val BASE_URL = "https://6731f6c17aaf2a9aff12e58d.mockapi.io/" // 지금 mockup
    private const val BASE_URL = "http://192.168.226.116:8080/"

    val apiService: ProblemApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // JSON 변환을 위한 Gson 추가
            .build()
            .create(ProblemApiService::class.java)
    }

    val settingsApiService: SettingsApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SettingsApiService::class.java)
    }

    val loginApiService: LoginApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginApiService::class.java)

    }

}







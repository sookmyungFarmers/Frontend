package com.example.haeun.com.haeun.sprinkler1.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://6731f6c17aaf2a9aff12e58d.mockapi.io/" // 실제 백엔드 URL로 변경, 지금 mockup

    val apiService: ProblemApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // JSON 변환을 위한 Gson 추가
            .build()
            .create(ProblemApiService::class.java)
    }
}
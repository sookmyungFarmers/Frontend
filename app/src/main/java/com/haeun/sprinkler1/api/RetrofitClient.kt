package com.example.haeun.com.haeun.sprinkler1.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private const val BASE_URL = "http://172.20.5.189:8080/questions/"

    // OkHttpClient 설정
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS) // 연결 시간 초과
        .readTimeout(30, TimeUnit.SECONDS)    // 읽기 시간 초과
        .writeTimeout(30, TimeUnit.SECONDS)   // 쓰기 시간 초과
        .hostnameVerifier { _, _ -> true }    // SSL 인증서 검증 우회 (테스트용)
        .build()

    // Retrofit 클라이언트 설정
    val apiService: ProblemApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client) // OkHttpClient 설정 추가
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProblemApiService::class.java)
    }
}

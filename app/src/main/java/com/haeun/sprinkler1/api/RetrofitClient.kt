package com.example.haeun.com.haeun.sprinkler1.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    // private const val BASE_URL = "https://6731f6c17aaf2a9aff12e58d.mockapi.io/" // 실제 백엔드 URL로 변경, 지금 mockup
    // private const val BASE_URL = "myapp://callback"
    private const val BASE_URL1 = "https://github.com/"

    private const val BASE_URL = "http://172.20.5.54:8080/" // 실제 서버 URL

    // OkHttpClient 설정
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS) // 연결 시간 초과
        .readTimeout(30, TimeUnit.SECONDS)    // 읽기 시간 초과
        .writeTimeout(30, TimeUnit.SECONDS)   // 쓰기 시간 초과
        .build()

    // Retrofit 클라이언트 설정
    val apiService: ProblemApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL1)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProblemApiService::class.java)
    }

    val settingsApiService: SettingsApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL1)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SettingsApiService::class.java)
    }

    val loginApiService: LoginApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL1)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginApiService::class.java)

    }

    val githubApiService: GithubApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubApiService::class.java)

    }



}

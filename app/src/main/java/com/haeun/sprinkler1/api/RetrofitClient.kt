package com.example.haeun.com.haeun.sprinkler1.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private const val BASE_URL2 = "https://github.com/"
    private const val BASE_URL1 = "http://192.168.19.248:8080/" // 실제 서버 URL

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

    val readMeApiService: ReadMeApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL1)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ReadMeApiService::class.java)
    }

    val githubApiService: GithubApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubApiService::class.java)

    }

    val settingsApiService: SettingsApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL2)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SettingsApiService::class.java)
    }

    val loginApiService: LoginApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL2)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginApiService::class.java)

    }

}
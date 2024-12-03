package com.example.haeun.com.haeun.sprinkler1.api

import com.example.haeun.com.haeun.sprinkler1.api.LoginApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import com.google.gson.GsonBuilder

object RetrofitClient {
    // private const val BASE_URL = "https://6731f6c17aaf2a9aff12e58d.mockapi.io/" // 실제 백엔드 URL로 변경, 지금 mockup
    // private const val BASE_URL = "myapp://callback"
    private const val BASE_URL = "https://github.com/"

    val apiService: ProblemApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
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

    val githubApiService: GithubApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubApiService::class.java)

    }



}

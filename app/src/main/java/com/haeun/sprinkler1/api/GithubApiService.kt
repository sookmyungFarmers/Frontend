package com.example.haeun.com.haeun.sprinkler1.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

// Retrofit API 인터페이스
interface GithubApiService {
    @GET("users/{username}/events")
    fun getUserEvents(
        @Path("username") username: String,
        @Header("Authorization") authHeader: String
    ): Call<List<GitHubEvent>>
}

// GitHubEvent 데이터 클래스
data class GitHubEvent(
    val type: String,
    val created_at: String
)
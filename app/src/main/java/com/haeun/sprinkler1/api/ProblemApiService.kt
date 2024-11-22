package com.example.haeun.com.haeun.sprinkler1.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProblemApiService {

    @GET("/questions/{question_id}")
    suspend fun getQuestionById(@Path("question_id") questionId: Long): Question
}
package com.example.haeun.com.haeun.sprinkler1.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProblemApiService {

    @GET("questions")  // 모든 문제 목록을 가져오는 엔드포인트
    fun getAllQuestions(): Call<List<Question>>

    // 특정 문제 정보를 가져오는 API 정의
    @GET("questions/{question_id}")
    fun getQuestion(@Path("question_id") questionId: Int): Call<Question>
}
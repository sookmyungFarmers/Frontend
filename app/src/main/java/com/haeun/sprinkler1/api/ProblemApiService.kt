package com.example.haeun.com.haeun.sprinkler1.api

import retrofit2.Call
import retrofit2.http.*

interface ProblemApiService {

    // 새로운 문제 생성
    @POST("questions")
    fun createQuestion(@Body question: Question): Call<Question>

    // 특정 문제 정보 조회
    @GET("questions/{question_id}")
    fun getQuestionById(@Path("question_id") questionId: Long): Call<Question>

    // 특정 문제 정보 수정
    @PUT("questions/{question_id}")
    fun updateQuestion(@Path("question_id") questionId: Long, @Body updatedQuestion: Question): Call<Question>

    // 특정 문제 삭제
    @DELETE("questions/{question_id}")
    fun deleteQuestion(@Path("question_id") questionId: Long): Call<Void>

    // 커밋할 문제 선택
    @POST("questions/{question_id}/commit")
    fun commitQuestion(@Path("question_id") questionId: Long): Call<Void>

    // 내가 선택한 정답 확인
    @PUT("questions/{question_id}/answer")
    fun checkAnswer(
        @Path("question_id") questionId: Long,
        @Query("userAnswer") userAnswer: Boolean
    ): Call<Question>
}

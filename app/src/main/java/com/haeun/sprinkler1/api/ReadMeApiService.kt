package com.example.haeun.com.haeun.sprinkler1.api

import retrofit2.Call
import retrofit2.http.*

interface ReadMeApiService {

    // 1. 새로운 리드미 작성
    @POST("/readme")
    fun createReadMe(@Body readMe: ReadMe): Call<ReadMe>

    // 2. 특정 문제의 리드미 조회
    @GET("/readme/{userId}/{question_id}/{readme_id}")
    fun getReadMe(
        @Path("userId") userId: Long,
        @Path("question_id") questionId: Long,
        @Path("readme_id") readMeId: Long
    ): Call<ReadMe>

    // 3. 특정 문제의 리드미 수정
    @PUT("/readme/{userId}/{question_id}/{readme_id}")
    fun updateReadMe(
        @Path("userId") userId: Long,
        @Path("question_id") questionId: Long,
        @Path("readme_id") readMeId: Long,
        @Body updatedReadMe: ReadMe
    ): Call<ReadMe>

    // 4. 특정 문제의 리드미 삭제
    @DELETE("/readme/{userId}/{question_id}/{readme_id}")
    fun deleteReadMe(
        @Path("userId") userId: Long,
        @Path("question_id") questionId: Long,
        @Path("readme_id") readMeId: Long
    ): Call<Void>
}

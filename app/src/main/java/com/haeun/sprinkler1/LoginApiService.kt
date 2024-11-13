package com.example.haeun.com.haeun.sprinkler1

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

//import retrofit2.http.GET
//import retrofit2.http.Path

// 요청에 사용할 데이터 클래스 정의
data class AccessTokenRequest(
    val client_id: String,
    val client_secret: String,
    val code: String
)

data class AccessTokenResponse(
    val access_token: String,
    val token_type: String
)

interface LoginApiService {

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    fun getAccessToken(
        @Body request: AccessTokenRequest
    ): Call<AccessTokenResponse>
}


package com.example.haeun.com.haeun.sprinkler1.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

data class ProfileRequest(val profileData: String)
data class CommitRequest(val todayCommit: Boolean)


interface LoginApiService {

    @Headers("Content-Type: application/json")

    // 깃허브 로그인 : 백엔드에서 GitHub로부터 액세스 토큰을 받아 사용자 정보를 반환
    @POST("/callback")
    fun getUserInfo(@Query("code") code: String): Call<User>

    // 백엔드에서 로그인된 사용자 정보를 반환하는 엔드포인트. 로그인 이후 사용자 정보가 필요할 때 사용
    @GET("/user")
    fun getLoggedInUserInfo(@Query("access_token") accessToken: String): Call<User>


//    @GET("/success")
//    fun GetUserInfo() : Response<User>

    // 깃허브 계정 있는지 확인
    // @GET("/user/{userId}")
    // fun checkGithubAccount(@Path("userId") userId: Long): Call<ResponseMessage>

    // 깃허브 레포지토리 만들기
    // @POST("/{userId}/git")
    // fun createGithubRepository(@Path("userId") userId: Long): Call<ResponseMessage>

    // 깃허브 프로필 연동
    @PUT("/{userId}/profile")
    fun updateProfile(@Path("userId") userId: Long, @Body request: ProfileRequest): Call<ResponseMessage>

    // 일일 커밋 여부 수정(인데 사용 안함)
    @PUT("/{userId}/todayCommit")
    fun updateTodayCommit(@Path("userId") userId: Long, @Body request: CommitRequest): Call<ResponseMessage>


//    // 연속 커밋 일수 세기
//    @PUT("/{userId}/seqAttend")
//    fun updateSeqAttend(@Path("userId") userId: Long): Call<ResponseMessage>

}
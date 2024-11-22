package com.example.haeun.com.haeun.sprinkler1.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path

data class AlarmTimeRequest(val userId: Long, val alarmTime: String)
data class AlarmOnRequest(val userId: Long, val alarmOn: Boolean)
data class LanguageRequest(val userId: Long, val language: String)
data class ResponseMessage(val message: String)

interface SettingsApiService {

    // 알람 시간 설정 정보를 가져오는 API 정의
    @PUT("/settings/{userId}/alarm")
    fun updateAlarmTime(@Path("userId") userId: Long, @Body request: AlarmTimeRequest): Call<ResponseMessage>

    // 알람 On/Off 설정 정보를 가져오는 API 정의
    @PUT("/settings/{userId}/alarm")
    fun updateAlarmOn(@Path("userId") userId: Long, @Body request: AlarmOnRequest): Call<ResponseMessage>

    // 언어 선택 정보를 가져오는 API 정의
    @PUT("/settings/{userId}/lang")
    fun updateLanguage(@Path("userId") userId: Long, @Body request: LanguageRequest): Call<ResponseMessage>
    
}

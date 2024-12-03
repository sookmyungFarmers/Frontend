package com.example.haeun.com.haeun.sprinkler1.api
import com.google.gson.annotations.SerializedName

//data class User(
//    val user_id: Int,
//    val time: Int,
//    val username: String, //깃허브 아이디 (githubId)
//    val nickname: String,
//    val profileImg: String,//프사 링크
//    val todayCommit: Boolean,
//    val seqAttendance: Int
//)

data class User(
    @SerializedName("login") val username: String,       // GitHub 아이디
    @SerializedName("id") val user_id: Int,             // GitHub 사용자 ID
    @SerializedName("avatar_url") val profileImg: String, // 프로필 이미지 URL
    @SerializedName("name") val nickname: String?        // GitHub 이름 (Optional)
)


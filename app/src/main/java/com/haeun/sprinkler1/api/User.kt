package com.example.haeun.com.haeun.sprinkler1.api

data class User(
    val user_id: Int,
    val time: Int,
    val username: String, //깃허브 아이디 (githubId)
    val nickname: String,
    val profileImg: String,//프사 링크
    val todayCommit: Boolean,
    val seqAttendance: Int
)


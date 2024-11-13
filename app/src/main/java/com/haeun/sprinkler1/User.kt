package com.example.haeun.com.haeun.sprinkler1

data class User(
    val username: String,
    val githubID: String,
    val time: Int,
    val todayCommit: Boolean,
    val seqAttendance: Int
)
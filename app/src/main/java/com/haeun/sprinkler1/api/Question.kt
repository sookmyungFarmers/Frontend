package com.example.haeun.com.haeun.sprinkler1.api

data class Question(
    val title: String,
    val description: String,
    val algorithmType: String,
    val difficulty: String,
    val isCommit: Boolean,
    val myChoice: String,
    val isCorrect: Boolean,
    val questionID: Int,
)
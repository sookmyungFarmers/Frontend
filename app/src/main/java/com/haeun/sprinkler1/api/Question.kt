package com.example.haeun.com.haeun.sprinkler1.api

data class Question(
    val question_id: Long,
    val title: String,
    val description: String,
    val difficulty: String,
    val isCommit: Boolean,
    val isCorrect: Boolean
)

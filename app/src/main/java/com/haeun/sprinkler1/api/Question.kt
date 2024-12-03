package com.example.haeun.com.haeun.sprinkler1.api

data class Question(
    val question_id: Long,
    val solution: Solution,
    val title: String,
    val description: String,
    val difficulty: String,
    val date: String,
    val isCommit: Boolean,
    val isCorrect: Boolean,
    val algorithmType: AlgorithmType
)

data class Solution(
    val solutionId: Int,
    val codeFile: String,
    val solutionTxt: String,
    val correctAns: Int
)

data class AlgorithmType(
    val title: String,
    val status: String,
    val algoTypeId: Int
)

data class SelectedAnswer(
    val selectedAnswer: Int
)

data class AnswerResult(
    val isCorrect: Boolean,
    val message: String
)

package com.example.haeun.com.haeun.sprinkler1.api

data class Question(
    val question_id: Int,
    val solution: Solution,
    val title: String,
    val description: String,
    val difficulty: String,
    val isCommit: Boolean,
    val isCorrect: Boolean,
    val algorithmType: AlgorithmType
)

data class Solution(
    val solution_id: Int,
    val codeFile: String,
    val solutionTxt: String,
    val correctAns: Int
)

data class AlgorithmType(
    val title: String,
    val status: String,
    val algoTypeId: Int,
)

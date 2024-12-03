package com.example.haeun.com.haeun.sprinkler1.api

data class ReadMe(
    val readme_id: Long,
    val user: User?, // 사용자 정보가 null일 수 있음
    val question: Question?, // 서버 응답에서 null 가능성을 고려
    val solution: Solution?, // 서버 응답에서 null 가능성을 고려
    val comment: String?,
    val star: Int?
)

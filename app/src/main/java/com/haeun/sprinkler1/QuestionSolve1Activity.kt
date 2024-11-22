package com.haeun.sprinkler1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.haeun.com.haeun.sprinkler1.api.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import java.net.SocketTimeoutException
import java.net.ConnectException
import java.net.NoRouteToHostException

class QuestionSolve1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_question_solve1)

        // 종료 버튼
        val endButton = findViewById<Button>(R.id.endButton)
        endButton.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java)
            startActivity(intent)
        }

        // 문제풀기 버튼
        val solveButton = findViewById<Button>(R.id.solveButton)
        solveButton.setOnClickListener {
            val intent = Intent(this, QuestionSolve2Activity::class.java)
            startActivity(intent)
        }

        val titleTextView: TextView = findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = findViewById(R.id.descriptionTextView)

        // 특정 문제 ID 가져오기 (예시: Intent로 전달된 ID 사용)
        val questionId = intent.getLongExtra("questionId", -1L)
        if (questionId != -1L) {
            fetchQuestionById(questionId, titleTextView, descriptionTextView)
        } else {
            Toast.makeText(this, "문제 ID를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchQuestionById(questionId: Long, titleTextView: TextView, descriptionTextView: TextView) {
        // 코루틴으로 Retrofit suspend 함수 호출
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val question = RetrofitClient.apiService.getQuestionById(questionId) // suspend 함수 호출
                withContext(Dispatchers.Main) {
                    // UI 업데이트
                    titleTextView.text = question.title
                    descriptionTextView.text = question.description
                }
            } catch (e: Exception) {
                // 예외 처리
                withContext(Dispatchers.Main) {
                    val errorMessage = when (e) {
                        is HttpException -> "HTTP 오류: ${e.code()} - ${e.message()}"
                        is UnknownHostException -> "호스트를 찾을 수 없습니다: ${e.message}"
                        is SocketTimeoutException -> "서버 응답 시간이 초과되었습니다."
                        is ConnectException -> "서버에 연결할 수 없습니다: ${e.message}"
                        is NoRouteToHostException -> "호스트로의 경로를 찾을 수 없습니다: ${e.message}"
                        is IOException -> "네트워크 연결 문제: ${e.message}"
                        else -> "알 수 없는 오류 발생: ${e.message}"
                    }

                    // 사용자에게 메시지 표시
                    Toast.makeText(this@QuestionSolve1Activity, errorMessage, Toast.LENGTH_LONG).show()

                    // 디버깅 로그 기록
                    Log.e("NetworkError", "Exception 발생: ${e.localizedMessage}", e)
                }
            }
        }
    }
}


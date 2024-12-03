package com.haeun.sprinkler1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.haeun.com.haeun.sprinkler1.api.Question
import com.example.haeun.com.haeun.sprinkler1.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionSolve1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_question_solve1)

        // 종료 버튼
        val endButton: Button = findViewById(R.id.endButton)
        endButton.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java)
            startActivity(intent)
        }

        // 제목과 내용을 표시할 TextView
        val titleTextView: TextView = findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = findViewById(R.id.descriptionTextView)

        // 문제풀기 버튼
        val solveButton: Button = findViewById(R.id.solveButton)
        solveButton.setOnClickListener {
            val intent = Intent(this, QuestionSolve2Activity::class.java).apply {
                putExtra("QUESTION_TITLE", titleTextView.text.toString())
                putExtra("QUESTION_DESCRIPTION", descriptionTextView.text.toString())
            }
            startActivity(intent)
        }

        // 특정 문제 ID 가져오기
        val questionId = intent.getLongExtra("question_id", 1L) // 기본값 1
        fetchQuestionById(questionId, titleTextView, descriptionTextView)
    }

    private fun fetchQuestionById(questionId: Long, titleTextView: TextView, descriptionTextView: TextView) {
        RetrofitClient.apiService.getQuestionById(questionId).enqueue(object : Callback<Question> {
            override fun onResponse(call: Call<Question>, response: Response<Question>) {
                if (response.isSuccessful) {
                    val question = response.body()
                    if (question != null) {
                        // UI 업데이트
                        titleTextView.text = question.title
                        descriptionTextView.text = question.description
                    } else {
                        showToast("문제 데이터를 찾을 수 없습니다.")
                    }
                } else {
                    // 서버에서 반환된 에러 메시지 확인
                    val errorBody = response.errorBody()?.string()
                    Log.e("API_ERROR", "서버 오류 발생: $errorBody")
                    Toast.makeText(
                        this@QuestionSolve1Activity,
                        "서버 오류 발생: $errorBody",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<Question>, t: Throwable) {
                Log.e("QuestionSolve1Activity", "네트워크 오류 발생", t)
                showToast("네트워크 오류: ${t.message}")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

package com.haeun.sprinkler1

import android.content.Intent
import android.os.Bundle
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
import kotlin.random.Random


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
            // 문제풀기 버튼 클릭 시 실행할 코드 작성

        }

        val titleTextView: TextView = findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = findViewById(R.id.descriptionTextView)

        fetchRandomQuestion(titleTextView, descriptionTextView)
    }


    private fun fetchRandomQuestion(titleTextView: TextView, descriptionTextView: TextView) {
        // 모든 문제 목록 가져오기
        RetrofitClient.apiService.getAllQuestions().enqueue(object : Callback<List<Question>> {
            override fun onResponse(call: Call<List<Question>>, response: Response<List<Question>>) {
                if (response.isSuccessful) {
                    val questionList = response.body()
                    if (!questionList.isNullOrEmpty()) {
                        // 목록에서 랜덤으로 문제 선택
                        val randomQuestion = questionList[Random.nextInt(questionList.size)]
                        titleTextView.text = randomQuestion.title
                        descriptionTextView.text = randomQuestion.description
                    } else {
                        Toast.makeText(this@QuestionSolve1Activity, "문제 목록이 비어 있습니다.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@QuestionSolve1Activity, "문제 목록을 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Question>>, t: Throwable) {
                Toast.makeText(this@QuestionSolve1Activity, "네트워크 오류가 발생했습니다: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
package com.haeun.sprinkler1

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.haeun.com.haeun.sprinkler1.api.Question
import com.example.haeun.com.haeun.sprinkler1.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionSolve2Activity : AppCompatActivity() {

    private lateinit var timer: CountDownTimer
    private val timeLimit = 10000L // 제한 시간: 10초 (10000ms)
    private lateinit var remainingTimeTextView: TextView // 남은 시간 표시 TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_question_solve2)

        // 종료 버튼
        val endButton = findViewById<TextView>(R.id.endButton)
        endButton.setOnClickListener {
            val intent = Intent(this, TodayActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 제목과 내용 표시 TextView
        val titleTextView: TextView = findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = findViewById(R.id.descriptionTextView)

        // Intent로 전달된 데이터 받아오기
        val questionTitle = intent.getStringExtra("QUESTION_TITLE") ?: "문제 제목 없음"
        val questionDescription = intent.getStringExtra("QUESTION_DESCRIPTION") ?: "문제 내용 없음"
        val questionId = intent.getLongExtra("QUESTION_ID", 1L) // 전달된 question_id

        // TextView 업데이트
        titleTextView.text = questionTitle
        descriptionTextView.text = questionDescription

        // 남은 시간 표시 TextView
        remainingTimeTextView = findViewById(R.id.remainingTimeTextView)

        // 선택지 버튼 클릭 이벤트
        val choice1: TextView = findViewById(R.id.choice1)
        val choice2: TextView = findViewById(R.id.choice2)

        choice1.setOnClickListener {
            submitAnswer(questionId, true) // 1번 선택지: true로 전달
        }

        choice2.setOnClickListener {
            submitAnswer(questionId, false) // 2번 선택지: false로 전달
        }

        // 타이머 시작
        startTimer()
    }

    private fun submitAnswer(questionId: Long, userAnswer: Boolean) {
        // Retrofit을 사용하여 API 호출
        RetrofitClient.apiService.checkAnswer(questionId, userAnswer).enqueue(object : Callback<Question> {
            override fun onResponse(call: Call<Question>, response: Response<Question>) {
                if (response.isSuccessful) {
                    val question = response.body()
                    if (question != null) {
                        // 서버에서 반환된 isCorrect 값을 확인하고 알림창 표시
                        showAlertDialog(if (question.isCorrect) "정답입니다!" else "오답입니다!")
                    } else {
                        showAlertDialog("응답이 비어 있습니다.")
                    }
                } else {
                    showAlertDialog("오류 발생: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Question>, t: Throwable) {
                showAlertDialog("네트워크 오류: ${t.message}")
            }
        })
    }

    private fun showAlertDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("결과")
        builder.setMessage(message)
        builder.setPositiveButton("확인") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun startTimer() {
        timer = object : CountDownTimer(timeLimit, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // 남은 시간 계산 및 TextView 업데이트
                val secondsRemaining = millisUntilFinished / 1000
                remainingTimeTextView.text = "남은 시간: ${secondsRemaining}초"
            }

            override fun onFinish() {
                showTimeoutDialog()
            }
        }.start()
    }

    private fun showTimeoutDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("정해진 학습시간이 끝났습니다")
        builder.setMessage("학습이 필요하시다면 연장을 눌러주세요")

        builder.setPositiveButton("연장") { dialog, _ ->
            dialog.dismiss()
            startTimer() // 시간을 다시 연장하여 타이머 재시작
        }

        builder.setNegativeButton("종료") { dialog, _ ->
            dialog.dismiss()
            // 종료 시 TodayActivity로 전환
            val intent = Intent(this, TodayActivity::class.java)
            startActivity(intent)
            finish()
        }

        val dialog = builder.create()
        dialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel() // Activity가 종료될 때 타이머 중지
    }
}

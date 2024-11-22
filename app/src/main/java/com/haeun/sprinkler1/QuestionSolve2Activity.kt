package com.haeun.sprinkler1

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.haeun.com.haeun.sprinkler1.api.Question
import com.example.haeun.com.haeun.sprinkler1.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

class QuestionSolve2Activity : AppCompatActivity() {

    private lateinit var timer: CountDownTimer
    private val timeLimit = 10000L // 제한 시간: 예를 들어, 10초 (10000밀리초)
    private lateinit var remainingTimeTextView: TextView // 남은 시간 표시 TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_question_solve2)

        // 종료 버튼
        val endButton = findViewById<Button>(R.id.endButton)
        endButton.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java)
            startActivity(intent)
        }

        val titleTextView: TextView = findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = findViewById(R.id.descriptionTextView)

        // Intent로 전달된 데이터 받아오기
        val questionTitle = intent.getStringExtra("QUESTION_TITLE") ?: "문제 제목 없음"
        val questionDescription = intent.getStringExtra("QUESTION_DESCRIPTION") ?: "문제 내용 없음"

        // 받아온 데이터를 화면에 표시
        titleTextView.text = questionTitle
        descriptionTextView.text = questionDescription


        // XML에 정의된 남은 시간 표시 TextView 연결
        remainingTimeTextView = findViewById(R.id.remainingTimeTextView)

        // 타이머 시작
        startTimer()
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
            // TodayActivity로 전환
            val intent = Intent(this, TodayActivity::class.java)
            startActivity(intent)
            finish() // 현재 Activity 종료
        }

        val dialog = builder.create()
        dialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel() // Activity가 종료될 때 타이머 중지
    }
}
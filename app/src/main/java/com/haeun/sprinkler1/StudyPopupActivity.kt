package com.haeun.sprinkler1

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class StudyPopupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_study_popup)

        val message_text: TextView = findViewById(R.id.message_text)

        // StudytimeActivity에서 전달한 시간을 받아서 표시
        val studyTime = intent.getIntExtra("STUDY_TIME", 30) // 기본 값 30분
        message_text.text = "${studyTime}분으로 설정하였습니다."

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // 3초 후에 MainActivity로 이동
        Handler(Looper.getMainLooper()).postDelayed({
            // MainActivity로 이동하는 Intent 생성
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // PopupActivity 종료
        }, 3000) // 3초 딜레이
    }
}

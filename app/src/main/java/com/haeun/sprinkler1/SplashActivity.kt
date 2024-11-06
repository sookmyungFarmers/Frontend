package com.haeun.sprinkler1

// SplashActivity: 앱이 시작될 때 로그인 상태를 확인하고, 로그인 상태에 따라 로그인 화면 또는 메인 화면으로 이동

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        // 딜레이 설정 (예: 3초)
        Handler(Looper.getMainLooper()).postDelayed({


            // 일단 계속 loginActivity 나오게 설정
            startActivity(Intent(this, LoginActivity::class.java))
            finish() // SplashActivity 종료

//            // 로그인 상태 확인
//            if (PreferenceManager.isLoggedIn(this)) {
//                // 이미 로그인 상태라면 바로 메인 화면으로 이동
//                startActivity(Intent(this, MainActivity::class.java))
//            } else {
//                // 로그인하지 않은 상태라면 로그인 화면으로 이동
//                startActivity(Intent(this, LoginActivity::class.java))
//            }
//            finish() // SplashActivity 종료

        }, 3000) // 3000ms = 3초


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}




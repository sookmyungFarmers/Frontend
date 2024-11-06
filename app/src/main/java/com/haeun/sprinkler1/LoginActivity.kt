package com.haeun.sprinkler1

// LoginActivity: 사용자가 로그인할 때 SharedPreferences에 로그인 상태를 저장하고, 메인 화면으로 이동

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 로그인 버튼 클릭 시 (로그인 성공 시)
        val loginButton: Button = findViewById(R.id.loginButton)
        loginButton.setOnClickListener {
            // 실제 로그인 로직 (아이디, 비밀번호 체크) 필요
            // 로그인 성공 시 SharedPreferences에 로그인 상태 저장
            com.example.haeun.PreferenceManager.setLoggedIn(this, true)

            // 메인 화면으로 이동
            startActivity(Intent(this, StudytimeActivity::class.java))
            finish() // LoginActivity 종료
        }

        // GitHub 계정이 없어요 버튼 클릭 시
        val noAccountButton: Button = findViewById(R.id.noAccountButton)
        noAccountButton.setOnClickListener {

            // GitLoginActivity로 이동
            startActivity(Intent(this, GitLoginActivity::class.java))
            finish() // LoginActivity 종료

        }

    }

}
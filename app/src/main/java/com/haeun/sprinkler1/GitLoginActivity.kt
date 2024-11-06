package com.haeun.sprinkler1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GitLoginActivity : AppCompatActivity() {

    // 이동할 GitHub URL
    private val githubAuthUrl = "https://github.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_git_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // GitHub 계정 만들기 버튼 클릭 시
        val makeGitButton: Button = findViewById(R.id.makeGitButton)

        makeGitButton.setOnClickListener {

            // URL을 포함한 Intent 생성
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(githubAuthUrl))
            // Intent를 사용해 기본 브라우저로 이동
            startActivity(intent)

        }

    }

}

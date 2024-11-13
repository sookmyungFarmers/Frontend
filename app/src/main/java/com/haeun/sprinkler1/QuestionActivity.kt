package com.haeun.sprinkler1

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        val homeButton = findViewById<LinearLayout>(R.id.homeButton)
        val reviewButton = findViewById<LinearLayout>(R.id.reviewButton)
        val friendsButton = findViewById<LinearLayout>(R.id.friendsButton)
        val profileButton = findViewById<LinearLayout>(R.id.profileButton)

        // 알고리즘 유형별 학습
        val algorithmStudyButton = findViewById<TextView>(R.id.algorithmStudyView)

        homeButton.setOnClickListener {
            // Home 버튼 클릭 시 처리할 작업
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        reviewButton.setOnClickListener {
            // Review 버튼 클릭 시 처리할 작업
            val intent = Intent(this, ReviewActivity::class.java)
            startActivity(intent)
        }

        friendsButton.setOnClickListener {
            // Friends 버튼 클릭 시 처리할 작업
            val intent = Intent(this, FriendsActivity::class.java)
            startActivity(intent)
        }

        profileButton.setOnClickListener {
            // Profile 버튼 클릭 시 처리할 작업
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        algorithmStudyButton.setOnClickListener {
            // 알고리즘 유형별 학습 클릭 시 처리할 작업
            val intent = Intent(this, AlgorithmStudyActivity::class.java)
            startActivity(intent)

        }
    }
}
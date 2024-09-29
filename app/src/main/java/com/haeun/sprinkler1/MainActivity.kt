package com.haeun.sprinkler1

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 네비게이션 버튼 클릭 이벤트 설정
        val homeButton = findViewById<LinearLayout>(R.id.homeButton)
        val questionButton = findViewById<LinearLayout>(R.id.questionButton)
        val reviewButton = findViewById<LinearLayout>(R.id.reviewButton)
        val friendsButton = findViewById<LinearLayout>(R.id.friendsButton)
        val profileButton = findViewById<LinearLayout>(R.id.profileButton)

        homeButton.setOnClickListener {
            // Home 버튼 클릭 시 처리할 작업
        }

        questionButton.setOnClickListener {
            // Question 버튼 클릭 시 처리할 작업
        }

        reviewButton.setOnClickListener {
            // Review 버튼 클릭 시 처리할 작업
        }

        friendsButton.setOnClickListener {
            // Friends 버튼 클릭 시 처리할 작업
        }

        profileButton.setOnClickListener {
            // Profile 버튼 클릭 시 처리할 작업
        }
    }
}
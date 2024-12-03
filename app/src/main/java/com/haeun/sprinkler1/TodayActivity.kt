package com.haeun.sprinkler1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class TodayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_today)

        // 네비게이션 버튼 클릭 이벤트 설정
        val homeButton = findViewById<LinearLayout>(R.id.homeButton)
        val questionButton = findViewById<LinearLayout>(R.id.questionButton)
        val reviewButton = findViewById<LinearLayout>(R.id.reviewButton)
        val friendsButton = findViewById<LinearLayout>(R.id.friendsButton)
        val profileButton = findViewById<LinearLayout>(R.id.profileButton)

        homeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        questionButton.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java)
            startActivity(intent)
        }

        reviewButton.setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java)
            startActivity(intent)
        }

        friendsButton.setOnClickListener {
            val intent = Intent(this, FriendsActivity::class.java)
            startActivity(intent)
        }

        profileButton.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        // README TextView 클릭 이벤트
        val readMe1 = findViewById<TextView>(R.id.readme1)
        val readMe2 = findViewById<TextView>(R.id.readme)
        val readMe3 = findViewById<TextView>(R.id.readme3)
        val readMe4 = findViewById<TextView>(R.id.readme4)

        val onClickReadMe = { view: View ->
            val intent = Intent(this, ReadMeActivity::class.java)
            // 필요한 데이터 전달
            intent.putExtra("QUESTION_ID", 12345L) // 예제 questionId
            startActivity(intent)
        }

        readMe1.setOnClickListener(onClickReadMe)
        readMe2.setOnClickListener(onClickReadMe)
        readMe3.setOnClickListener(onClickReadMe)
        readMe4.setOnClickListener(onClickReadMe)
    }
}

package com.haeun.sprinkler1

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FriendsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        val homeButton = findViewById<LinearLayout>(R.id.homeButton)
        val questionButton = findViewById<LinearLayout>(R.id.questionButton)
        val reviewButton = findViewById<LinearLayout>(R.id.reviewButton)
        val profileButton = findViewById<LinearLayout>(R.id.profileButton)
        val haeunTreeButton = findViewById<TextView>(R.id.friend3)


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

        profileButton.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        haeunTreeButton.setOnClickListener {
            val intent = Intent(this, TreeHaeunActivity::class.java)
            startActivity(intent)
        }
    }
}
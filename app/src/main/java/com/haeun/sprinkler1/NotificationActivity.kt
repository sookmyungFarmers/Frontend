package com.haeun.sprinkler1

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity



class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notification)

        val notification_go_back = findViewById<ImageView>(R.id.notification_go_back)
        val learning_notification = findViewById<TextView>(R.id.learning_notification_label)

        notification_go_back.setOnClickListener {
            finish()
        }
        learning_notification.setOnClickListener {
            val intent = Intent(this, LearningNotificationActivity::class.java)
            startActivity(intent)
        }


    }
}
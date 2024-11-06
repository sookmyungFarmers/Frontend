package com.haeun.sprinkler1

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ThemeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_theme)

        val theme_go_back = findViewById<ImageView>(R.id.theme_go_back)
        val learning_theme_label = findViewById<TextView>(R.id.learning_theme_label)

        theme_go_back.setOnClickListener {
            finish()
        }

        learning_theme_label.setOnClickListener {
            // Home 버튼 클릭 시 처리할 작업
            val intent = Intent(this, ThemeColorActivity::class.java)
            startActivity(intent)
        }

    }
}
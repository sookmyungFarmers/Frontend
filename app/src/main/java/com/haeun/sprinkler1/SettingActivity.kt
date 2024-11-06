package com.haeun.sprinkler1

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.google.android.material.textview.MaterialTextView
import com.haeun.sprinkler1.R

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting2)

        // 각각의 항목을 findViewById로 가져오기
        val notification_setting = findViewById<MaterialTextView>(R.id.notification_setting)
        val theme_setting = findViewById<MaterialTextView>(R.id.theme_setting)
        val language_setting = findViewById<MaterialTextView>(R.id.language_setting)
        val account_management = findViewById<MaterialTextView>(R.id.account_management)

        notification_setting.setOnClickListener {
            // Home 버튼 클릭 시 처리할 작업
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
        }

        theme_setting.setOnClickListener {
            // Home 버튼 클릭 시 처리할 작업
            val intent = Intent(this, ThemeActivity::class.java)
            startActivity(intent)
        }

        language_setting.setOnClickListener {
            // Home 버튼 클릭 시 처리할 작업
            val intent = Intent(this, LanguageActivity::class.java)
            startActivity(intent)
        }

        account_management.setOnClickListener {
            // Home 버튼 클릭 시 처리할 작업
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }
    }

}
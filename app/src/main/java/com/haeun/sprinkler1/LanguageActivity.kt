package com.haeun.sprinkler1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LanguageActivity : AppCompatActivity() {

    private lateinit var pythonCheck: ImageView
    private lateinit var javaCheck: ImageView
    private lateinit var cppCheck: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_language)

        val language_go_back = findViewById<ImageView>(R.id.language_go_back)

        // 체크 표시 이미지뷰 가져오기
        pythonCheck = findViewById(R.id.python_check)
        javaCheck = findViewById(R.id.java_check)
        cppCheck = findViewById(R.id.cpp_check)

        // 언어 선택 텍스트뷰 가져오기
        val pythonLabel = findViewById<TextView>(R.id.python_label)
        val javaLabel = findViewById<TextView>(R.id.java_label)
        val cppLabel = findViewById<TextView>(R.id.cpp_label)

        language_go_back.setOnClickListener {
            finish()
        }

        // 클릭 리스너 설정
        pythonLabel.setOnClickListener {
            showCheck(pythonCheck)
        }

        javaLabel.setOnClickListener {
            showCheck(javaCheck)
        }

        cppLabel.setOnClickListener {
            showCheck(cppCheck)
        }
    }

    // 선택된 체크 표시만 보이게 설정하는 함수
    private fun showCheck(selectedCheck: ImageView) {
        // 모든 체크 표시 숨기기
        pythonCheck.visibility = View.GONE
        javaCheck.visibility = View.GONE
        cppCheck.visibility = View.GONE

        // 선택된 체크 표시만 보이기
        selectedCheck.visibility = View.VISIBLE
    }

}

package com.haeun.sprinkler1

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ThemeColorActivity : AppCompatActivity() {
    private lateinit var greenCheck: ImageView
    private lateinit var pinkCheck: ImageView
    private lateinit var blueCheck: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_theme_color)

        val language_go_back = findViewById<ImageView>(R.id.theme_color_go_back)

        // 체크 표시 이미지뷰 가져오기
        greenCheck = findViewById(R.id.green_check)
        pinkCheck = findViewById(R.id.pink_check)
        blueCheck = findViewById(R.id.blue_check)

        // 언어 선택 텍스트뷰 가져오기
        val greenLabel = findViewById<TextView>(R.id.green_label)
        val pinkLabel = findViewById<TextView>(R.id.pink_label)
        val blueLabel = findViewById<TextView>(R.id.blue_label)

        language_go_back.setOnClickListener {
            finish()
        }

        // 클릭 리스너 설정
        greenLabel.setOnClickListener {
            showCheck(greenCheck)
        }

        pinkLabel.setOnClickListener {
            showCheck(pinkCheck)
        }

        blueLabel.setOnClickListener {
            showCheck(blueCheck)
        }
    }

    // 선택된 체크 표시만 보이게 설정하는 함수
    private fun showCheck(selectedCheck: ImageView) {
        // 모든 체크 표시 숨기기
        greenCheck.visibility = View.GONE
        pinkCheck.visibility = View.GONE
        blueCheck.visibility = View.GONE

        // 선택된 체크 표시만 보이기
        selectedCheck.visibility = View.VISIBLE
    }
}
package com.haeun.sprinkler1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.haeun.sprinkler1.R
import com.haeun.sprinkler1.StudyPopupActivity

class StudytimeActivity : AppCompatActivity() {

    private var studyTime = 30 // 기본 값 30분

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_studytime)

        val timeTextView = findViewById<TextView>(R.id.timeTextView) // 30분 TextView
        val seekBar = findViewById<SeekBar>(R.id.timeSeekBar) // SeekBar
        val setButton: Button = findViewById(R.id.setButton)

        // SeekBar ChangeListener 설정
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // SeekBar의 진행 값에 따라 TextView 값 설정
                studyTime = progress + 10 // 최소 10분으로 설정
                timeTextView.text = "${studyTime}분"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // SeekBar가 터치되기 시작할 때 호출 (사용 안함)
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // SeekBar 터치가 끝났을 때 호출 (사용 안함)
            }
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // 버튼 클릭 리스너 설정
        setButton.setOnClickListener {
            // 메인 화면으로 이동
            val intent = Intent(this, StudyPopupActivity::class.java)
            intent.putExtra("STUDY_TIME", studyTime)
            startActivity(intent)
        }

    }
}


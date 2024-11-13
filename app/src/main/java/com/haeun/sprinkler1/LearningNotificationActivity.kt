package com.haeun.sprinkler1

import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class LearningNotificationActivity : AppCompatActivity() {

    private lateinit var timeTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_learning_notification)

        val study_notification_go_back = findViewById<ImageView>(R.id.study_notification_go_back)

        study_notification_go_back.setOnClickListener {
            finish()
        }

        // 시간 표시 TextView 초기화
        timeTextView = findViewById(R.id.timeTextView)

        // 기본 시간 설정 (8:30 AM)
        timeTextView.text = "8:30 AM"

        // 시간 설정을 위한 아이콘 클릭 이벤트 설정
        findViewById<TextView>(R.id.timeTextView).setOnClickListener {
            showTimePickerDialog()
        }

    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        // TimePickerDialog 생성 및 표시
        val timePickerDialog = TimePickerDialog(this, { _, selectedHour, selectedMinute ->
            val amPm = if (selectedHour < 12) "AM" else "PM"
            val formattedHour = if (selectedHour % 12 == 0) 12 else selectedHour % 12
            val formattedTime = String.format("%02d:%02d %s", formattedHour, selectedMinute, amPm)

            // 선택한 시간을 TextView에 설정
            timeTextView.text = formattedTime
        }, hour, minute, false) // false는 12시간 형식 사용

        timePickerDialog.show()
    }
}
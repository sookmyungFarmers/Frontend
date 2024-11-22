package com.haeun.sprinkler1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ReadMeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_me)

        // 상단 버튼 이벤트 처리
        val cancelButton = findViewById<Button>(R.id.cancelButton)
        val saveButton = findViewById<Button>(R.id.saveButton)

        cancelButton.setOnClickListener {
            finish() // 현재 화면 종료
        }

        saveButton.setOnClickListener {
            // 저장 기능 구현 (필요에 따라 추가)
        }

        // 문제 보기 버튼 이벤트 처리
        val viewProblemButton = findViewById<Button>(R.id.viewProblemButton)
        viewProblemButton.setOnClickListener {
            val intent = Intent(this, QuestionSolve2Activity::class.java)
            startActivity(intent)
        }

        // 문제 데이터 표시 (예제 데이터)
        val titleTextView: TextView = findViewById(R.id.readmeTitle)
        val solveDateTextView: TextView = findViewById(R.id.solveDate)
        val solveTimeTextView: TextView = findViewById(R.id.solveTime)
        val solveStatusTextView: TextView = findViewById(R.id.solveStatus)
        val solveDifficultyTextView: TextView = findViewById(R.id.solveDifficulty)
        val commentContentTextView: TextView = findViewById(R.id.commentContent)

        // 예제 데이터 설정
        titleTextView.text = "백준 - 문제 1. 최대 힙 #12345"
        solveDateTextView.text = "풀이일자: 2024.08.20 TUE"
        solveTimeTextView.text = "풀이시간: 12m"
        solveStatusTextView.text = "정답여부: Pass"
        solveDifficultyTextView.text = "난이도: Easy"
        commentContentTextView.text = "#heappush(heap, item)\n힙 삽입을 유지하면서 item 값을 heap으로 push하는 메소드\n\n#heappop(heap)\n최소힙의 루트값을 반환하고, heap에서 가장 작은 값을 제거하는 메소드"
    }
}
package com.haeun.sprinkler1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProblemExplanationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_problem_explanation)

        // Close 버튼 이벤트 처리
        val closeButton = findViewById<Button>(R.id.closeButton)
        closeButton.setOnClickListener {
            finish() // 현재 화면 종료
        }

        // 데이터 설정
        val explanationContentTextView: TextView = findViewById(R.id.explanationContent)
        val codeContentTextView: TextView = findViewById(R.id.codeContent)

        // 예제 데이터 (실제 데이터는 Intent나 API로 전달받아야 함)
        val explanationText = "headpop은 ~~~.\nheadpush는 ~~~ 이므로\n정답은 a. headpop, b. headpush 입니다."
        val codeText = """
            import sys
            input = sys.stdin.readline
            import heapq
            q = []

            n = int(input())
            for i in range(n):
                x = int(input())
                if x == 0:
                    if len(q) == 0:
                        print(0)
                    else:
                        print(heapq.heappop(q))
                else:
                    heapq.heappush(q, x)
        """.trimIndent()

        // 화면에 데이터 표시
        explanationContentTextView.text = explanationText
        codeContentTextView.text = codeText
    }
}
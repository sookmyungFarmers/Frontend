package com.haeun.sprinkler1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat

class GitHubGrassView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint()
    private val commitDates = mutableMapOf<String, Int>() // 커밋 데이터를 저장

    fun setCommitData(data: Map<String, Int>) {
        commitDates.clear()
        commitDates.putAll(data)
        invalidate() // 화면 갱신
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val cellSize = 30f // 각 셀의 크기
        val padding = 5f   // 셀 간 간격
        val daysInWeek = 7 // 한 주에 7일
        val totalWeeks = 52 // 1년 = 52주
        val calendar = java.util.Calendar.getInstance()

        val startX = padding
        var x = startX
        var y = padding

        // 1월 1일부터 시작
        calendar.set(java.util.Calendar.DAY_OF_YEAR, 1)

        // 1~26주는 건너뛰기
        for (dayIndex in 0 until (26 * daysInWeek)) {
            calendar.add(java.util.Calendar.DAY_OF_YEAR, 1) // 날짜를 건너뜀
        }

        // 27~52주를 그림
        for (dayIndex in 0 until (26 * daysInWeek)) {
            // 날짜 키 생성 (YYYY-MM-DD 형식)
            val dateKey = String.format(
                "%04d-%02d-%02d",
                calendar.get(java.util.Calendar.YEAR),
                calendar.get(java.util.Calendar.MONTH) + 1,
                calendar.get(java.util.Calendar.DAY_OF_MONTH)
            )

            // 기본 색상: 회색
            paint.color = Color.LTGRAY

            // 커밋 데이터에 해당 날짜가 있는 경우 색상 변경
            if (commitDates.containsKey(dateKey)) {
                val commitCount = commitDates[dateKey] ?: 0
                paint.color = when (commitCount) {
                    in 1..3 -> ContextCompat.getColor(context, R.color.main1)
                    in 4..6 -> ContextCompat.getColor(context, R.color.main2)
                    else -> Color.LTGRAY

                }
            }

            // 사각형(셀) 그리기
            canvas.drawRect(x, y, x + cellSize, y + cellSize, paint)

            // 다음 날짜로 이동
            calendar.add(java.util.Calendar.DAY_OF_YEAR, 1)

            // 다음 위치 계산
            y += cellSize + padding // 아래로 이동
            if ((dayIndex + 1) % daysInWeek == 0) {
                y = padding // 다시 위로
                x += cellSize + padding // 오른쪽으로 이동
            }
        }
    }


}

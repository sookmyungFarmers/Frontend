package com.haeun.sprinkler1

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// 1. 커밋한 날짜에 진한 초록으로 칠하기
// 2. 커밋 메시지 변경

class MainActivity : AppCompatActivity() {
    private lateinit var messageTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 메시지 TextView 가져오기
        messageTextView = findViewById(R.id.messageTextView)

        // 이 달의 잔디
        val calendarGrid = findViewById<GridLayout>(R.id.calendarGrid)

        // 날짜 생성 (1일부터 31일까지) -> 일 수 고려해야함
        for (day in 1..31) {
            val dayTextView = TextView(this).apply {
                text = day.toString()
                textSize = 16f
                setTextColor(Color.BLACK)
                gravity = Gravity.CENTER
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 35.dpToPx() // TextView의 크기
                    height = 35.dpToPx()
                    setMargins(6.dpToPx(), 6.dpToPx(), 6.dpToPx(), 6.dpToPx()) // 날짜 간격
                }
                setBackgroundResource(R.drawable.rounded_background_white)
            }
            calendarGrid.addView(dayTextView)
        }


        // 사용자 정보 요청
        //fetchUserInfoAndUpdateUI()

        val myGrass = findViewById<LinearLayout>(R.id.myGrass)
        val homeButton = findViewById<LinearLayout>(R.id.homeButton)
        val questionButton = findViewById<LinearLayout>(R.id.questionButton)
        val reviewButton = findViewById<LinearLayout>(R.id.reviewButton)
        val friendsButton = findViewById<LinearLayout>(R.id.friendsButton)
        val profileButton = findViewById<LinearLayout>(R.id.profileButton)


        homeButton.setOnClickListener {
            // Home 버튼 클릭 시 처리할 작업
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        questionButton.setOnClickListener {
            // Question 버튼 클릭 시 처리할 작업
            val intent = Intent(this, QuestionActivity::class.java)
            startActivity(intent)
        }

        reviewButton.setOnClickListener {
            // Review 버튼 클릭 시 처리할 작업
            val intent = Intent(this, ReviewActivity::class.java)
            startActivity(intent)
        }

        friendsButton.setOnClickListener {
            // Friends 버튼 클릭 시 처리할 작업
            val intent = Intent(this, FriendsActivity::class.java)
            startActivity(intent)
        }

        profileButton.setOnClickListener {
            // Profile 버튼 클릭 시 처리할 작업
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun Int.dpToPx(): Int {
        val density = resources.displayMetrics.density
        return (this * density).toInt()
    }

    override fun onResume() {
        super.onResume()
        messageTextView.text = "윤하은님, 오늘 문제풀이 완료했어요 :)"

    }
//    private fun fetchUserInfoAndUpdateUI() {
//        // RetrofitClient에서 LoginApiService 가져오기
//        val apiService = RetrofitClient.loginApiService
//
//        // 저장된 액세스 토큰 (이전 로그인 시 저장한 토큰을 가져옴)
//        val sharedPref = getSharedPreferences("auth", MODE_PRIVATE)
//        val accessToken = sharedPref.getString("access_token", null)
//
//        if (accessToken != null) {
//            // 사용자 정보 요청
//            apiService.getLoggedInUserInfo(accessToken).enqueue(object : Callback<User> {
//                override fun onResponse(call: Call<User>, response: Response<User>) {
//                    if (response.isSuccessful) {
//                        val user = response.body()
//                        if (user != null) {
//                            //updateMessageBasedOnCommitStatus(user.todayCommit)
//                        }
//                    } else {
//                        // 오류 처리 (예: 토큰 만료)
//                        messageTextView.text = "사용자 정보를 가져오지 못했습니다."
//                    }
//                }
//
//                override fun onFailure(call: Call<User>, t: Throwable) {
//                    // 네트워크 또는 서버 오류 처리
//                    messageTextView.text = "네트워크 오류가 발생했습니다."
//                }
//            })
//        } else {
//            // 액세스 토큰이 없는 경우
//            messageTextView.text = "로그인이 필요합니다."
//
//        }
//    }
//
//    private fun updateMessageBasedOnCommitStatus(todayCommit: Boolean) {
//
//        val currentDate = SimpleDateFormat("MM월 dd일", java.util.Locale.getDefault()).format(Calendar.getInstance().time)
//
//        // 커밋 상태에 따라 메시지 업데이트
//        if (todayCommit) {
//            messageTextView.text = "오늘 커밋 완료했어요 :)"
//        } else {
//            messageTextView.text = "$currentDate, 오늘은 아직 커밋하지 않았어요 :)"
//        }
//    }
}
package com.haeun.sprinkler1

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.haeun.com.haeun.sprinkler1.api.GitHubEvent
import com.example.haeun.com.haeun.sprinkler1.api.GithubApiService
import com.example.haeun.com.haeun.sprinkler1.api.RetrofitClient
import com.example.haeun.com.haeun.sprinkler1.api.RetrofitClient.githubApiService
import com.example.haeun.com.haeun.sprinkler1.api.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // TextView 참조
        val titleTextView4: TextView = findViewById(R.id.titleTextView4)
        titleTextView4.text = "윤하은님,"

        // GitHub API 호출
        val GitHubGrassView = findViewById<GitHubGrassView>(R.id.githubGrassImage)
        val token = "Bearer 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"
        val username = "haxxnshine"

        githubApiService.getUserEvents(username, token).enqueue(object : Callback<List<GitHubEvent>> {
            override fun onResponse(call: Call<List<GitHubEvent>>, response: Response<List<GitHubEvent>>) {
                if (response.isSuccessful) {
                    val events = response.body() ?: emptyList()
                    val commitDates = events.filter { it.type == "PushEvent" }
                        .groupBy { it.created_at.split("T")[0] }
                        .mapValues { it.value.size }

                    // 로그로 데이터 확인
                    Log.d("GitHubAPI", "Commit data: $commitDates")

                    // 잔디 데이터 View로 전달
                    GitHubGrassView.setCommitData(commitDates)

                    // 잔디 데이터 표시
//                    val githubGrassUrl = "https://ghchart.rshah.org/$username"
//                    Glide.with(this@ProfileActivity)
//                        .load(githubGrassUrl)
//                        .placeholder(R.drawable.loading)
//                        .error(R.drawable.warning)
//                        .into(githubGrassImage)
                } else {
                    Log.e("GitHubAPI", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<GitHubEvent>>, t: Throwable) {
                Log.e("GitHubAPI", "API call failed", t)
            }
        })



//        // 액세스 토큰 가져오기
//        val sharedPref = getSharedPreferences("auth", MODE_PRIVATE)
//        val accessToken = sharedPref.getString("access_token", null)
//
//        if (accessToken != null) {
//            // Retrofit을 통해 사용자 정보 요청
//            fetchUserInfo(accessToken, titleTextView4)
//        } else {
//            Toast.makeText(this, "로그인 상태를 확인할 수 없습니다.", Toast.LENGTH_SHORT).show()
//        }

        val homeButton = findViewById<LinearLayout>(R.id.homeButton)
        val questionButton = findViewById<LinearLayout>(R.id.questionButton)
        val reviewButton = findViewById<LinearLayout>(R.id.reviewButton)
        val friendsButton = findViewById<LinearLayout>(R.id.friendsButton)
        val settingButton = findViewById<TextView>(R.id.setting)

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

        settingButton.setOnClickListener {
            // SettingActivity로 이동
            Log.d("ProfileActivity", "Setting button clicked")
            startActivity(Intent(this, SettingActivity::class.java))
        }
    }

//    private fun fetchUserInfo(accessToken: String, titleTextView: TextView) {
//        // Retrofit 호출
//        val call = RetrofitClient.loginApiService.getLoggedInUserInfo(accessToken)
//
//        call.enqueue(object : Callback<User> {
//            override fun onResponse(call: Call<User>, response: Response<User>) {
//                if (response.isSuccessful) {
//                    val user = response.body()
//                    if (user != null) {
//                        // TextView에 username 업데이트
//                        titleTextView.text = "${user.username},"
//
//                        // GitHub 잔디 이미지 표시
//                        displayGithubGrass(user.username)
//
//                        Toast.makeText(this@ProfileActivity, "사용자 정보 로드 성공", Toast.LENGTH_SHORT).show()
//                    }
//                } else {
//                    Log.e("ProfileActivity", "Error: ${response.errorBody()}")
//                    Toast.makeText(this@ProfileActivity, "사용자 정보를 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<User>, t: Throwable) {
//                Log.e("ProfileActivity", "Error: ${t.localizedMessage}")
//                Toast.makeText(this@ProfileActivity, "네트워크 오류 발생", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }





}
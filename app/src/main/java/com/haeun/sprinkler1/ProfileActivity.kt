package com.haeun.sprinkler1

import android.content.Intent
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
import com.example.haeun.com.haeun.sprinkler1.api.RetrofitClient
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

        // 액세스 토큰 가져오기
        val sharedPref = getSharedPreferences("auth", MODE_PRIVATE)
        val accessToken = sharedPref.getString("access_token", null)

        if (accessToken != null) {
            // Retrofit을 통해 사용자 정보 요청
            fetchUserInfo(accessToken, titleTextView4)
        } else {
            Toast.makeText(this, "로그인 상태를 확인할 수 없습니다.", Toast.LENGTH_SHORT).show()
        }

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

    private fun fetchUserInfo(accessToken: String, titleTextView: TextView) {
        // Retrofit 호출
        val call = RetrofitClient.loginApiService.getLoggedInUserInfo(accessToken)

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    if (user != null) {
                        // TextView에 username 업데이트
                        titleTextView.text = "${user.username},"

                        // GitHub 잔디 이미지 표시
                        displayGithubGrass(user.username)

                        Toast.makeText(this@ProfileActivity, "사용자 정보 로드 성공", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e("ProfileActivity", "Error: ${response.errorBody()}")
                    Toast.makeText(this@ProfileActivity, "사용자 정보를 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("ProfileActivity", "Error: ${t.localizedMessage}")
                Toast.makeText(this@ProfileActivity, "네트워크 오류 발생", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun displayGithubGrass(username: String) {
        val githubGrassImageView = findViewById<ImageView>(R.id.githubGrassImage)
        val githubGrassUrl = "https://ghchart.rshah.org/$username" // GitHub 잔디 URL

        Glide.with(this)
            .load(githubGrassUrl)
            .placeholder(R.drawable.loading) // 로딩 중 표시할 이미지
            .error(R.drawable.warning)      // 오류 시 표시할 이미지
            .into(githubGrassImageView)
    }


}
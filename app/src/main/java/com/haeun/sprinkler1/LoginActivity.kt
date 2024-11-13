package com.haeun.sprinkler1

// LoginActivity: 사용자가 로그인할 때 SharedPreferences에 로그인 상태를 저장하고, 메인 화면으로 이동

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log

import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.haeun.com.haeun.sprinkler1.AccessTokenRequest
import com.example.haeun.com.haeun.sprinkler1.AccessTokenResponse
import com.example.haeun.com.haeun.sprinkler1.LoginApiService
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)


        // 로그인 버튼 클릭 시
        val loginButton: Button = findViewById(R.id.loginButton)
        loginButton.setOnClickListener {

            // 1. 깃허브 로그인 페이지로 리디렉션
            val intent = Intent(
                Intent.ACTION_VIEW, // 이 부분 구현
                Uri.parse("https://github.com/login/oauth/authorize?client_id=Iv23likDwEG91xj9jRgO&redirect_uri=myapp://callback&scope=user:email")
            )

            startActivity(intent)
            //startActivity(Intent(this, StudytimeActivity::class.java))
            finish() // LoginActivity 종료
        }

        //testButton StudytimeActivity
        val testButton: Button = findViewById(R.id.testButton)
        testButton.setOnClickListener {
            startActivity(Intent(this, StudytimeActivity::class.java))
        }


        // GitHub 계정이 없어요 버튼 클릭 시
        val noAccountButton: Button = findViewById(R.id.noAccountButton)
        noAccountButton.setOnClickListener {

            // GitLoginActivity로 이동
            startActivity(Intent(this, GitLoginActivity::class.java))
            finish() // LoginActivity 종료

        }

    }

    override fun onResume() {
        super.onResume()

        // 2. 인증 완료 후 리다이렉트 URI에서 authorization_code 추출
        val data = intent?.data
        if (data != null && data.toString().startsWith("myapp://callback")) {
            val code = data.getQueryParameter("code")

            if (code != null) {
                // 3. authorization_code를 백엔드로 전송하여 access_token 요청
                Log.d("GitHubAuth", "Authorization code: $code")
                requestAccessTokenFromBackend(code)
            }
            else{
                Log.d("GitHubAuth", "Authorization code not found in redirect URI")
            }
        } else{
            Log.d("GitHubAuth", "Redirect URI not matched or data is null")
        }
    }

    private fun requestAccessTokenFromBackend(code: String) {
        // 4. Retrofit/OkHttp 등을 사용하여 백엔드에 POST 요청
        val retrofit = Retrofit.Builder()
            .baseUrl("YOUR_BACKEND_URL") // BACKEND_URL 추가하기
            .build()
        val api = retrofit.create(LoginApiService::class.java)

        val request = AccessTokenRequest(
            client_id = "Iv23LikDwEG91xj9jRgO",
            client_secret = "cc3f206faa0429bcc84ae236b97a16d3ee997109",
            code = code
        )


        api.getAccessToken(request)
            .enqueue(object : Callback<AccessTokenResponse> {
                override fun onResponse(call: Call<AccessTokenResponse>, response: Response<AccessTokenResponse>) {
                    if (response.isSuccessful) {
                        val accessToken = response.body()?.access_token
                        // 5. access_token을 안전한 저장소에 저장
                        // saveAccessToken(accessToken)
                        // 6. 사용자 상태 업데이트
                        // updateUIForLoggedInUser()
                    }
                }

                override fun onFailure(call: Call<AccessTokenResponse>, t: Throwable) {
                    // 오류 처리
                }
            })
    }


//    // 6. 프론트엔드에서 access_token 수신 및 저장
//    private fun saveAccessToken(token: String?) {
//        val sharedPref = getSharedPreferences("auth", Context.MODE_PRIVATE)
//        sharedPref.edit().putString("access_token", token).apply()
//    }
//
//    // 7. 사용자 상태 관리 및 로그아웃 처리
//    private fun updateUIForLoggedInUser() {
//        // 로그인 상태로 UI 업데이트
//        Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
//        // 로그인 후 메인 화면으로 이동 또는 상태 표시
//    }

}
package com.haeun.sprinkler1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.haeun.PreferenceManager.isLoggedIn
import com.example.haeun.com.haeun.sprinkler1.api.RetrofitClient.loginApiService
import com.example.haeun.com.haeun.sprinkler1.api.User
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        // 로그인 상태 확인
        if (isLoggedIn()) {
            // 이미 로그인 상태라면 MainActivity로 이동
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        // 로그인 버튼 클릭 시
        val loginButton: Button = findViewById(R.id.loginButton)
        loginButton.setOnClickListener {

            // 깃허브 로그인 페이지로 리디렉션
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://github.com/login/oauth/authorize?client_id=Ov231idWBtsaUstT3jPV&redirect_uri=http://192.168.226.116:8080/login/oauth2/code/github&scope=user:email")
            )

            startActivity(intent)
            //startActivity(Intent(this, StudytimeActivity::class.java))
            finish() // LoginActivity 종료
        }

        // testButton StudytimeActivity
        val testButton: Button = findViewById(R.id.testButton)
        testButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
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

        // 인증 완료 후 리다이렉트 URI에서 authorization_code 추출
        val data = intent?.data

        Log.d("RedirectURI", "Intent data: $data")
        if (data != null && data.toString().startsWith("http://192.168.226.116:8080/login/oauth2/code/github")) {
            val code = data.getQueryParameter("code")
            Log.d("Code1111", "Code: $code")

            if (code != null) {
                // authorization_code를 백엔드로 전송하여 사용자 정보 요청
                requestUserInfoFromBackend(code)
            }
            else{
                Log.d("GitHubAuth", "Authorization code not found in redirect URI")
            }
        } else{
            Log.d("GitHubAuth", "Redirect URI not matched or data is null")
        }
    }

    private fun requestUserInfoFromBackend(code: String) {
        // Retrofit 인스턴스 생성
//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://localhost:3000/login/oauth2/code/github") //////////////////////////// 실제 백엔드 URL로 교체
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        val api = retrofit.create(LoginApiService::class.java)

        // 백엔드로 code를 전송하고 사용자 정보를 요청
        loginApiService.getUserInfo(code).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val token = response.headers()["Authorization"] // 헤더에서 토큰 가져오기 (예시)
                    saveAccessToken(token)
                    updateUIForLoggedInUser(response.body())
                    // 사용자 정보를 안전한 저장소에 저장하고, 로그인 상태로 UI 업데이트
                    Toast.makeText(this@LoginActivity, "로그인 성공!", Toast.LENGTH_SHORT).show()
                } else {
                    Log.e("GitHubAuth", "Failed to retrieve user info: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("GitHubAuth", "Error: ${t.localizedMessage}")
            }
        })
    }

    private fun saveAccessToken(token: String?) {
        if (token != null) {
            val sharedPref = getSharedPreferences("auth", MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("access_token", token)
            editor.apply()
        }
    }

    private fun isLoggedIn(): Boolean {
        val sharedPref = getSharedPreferences("auth", MODE_PRIVATE)
        return !sharedPref.getString("access_token", null).isNullOrEmpty()
    }

    private fun updateUIForLoggedInUser(User: User?) {
        // 로그인 성공 후 메인 화면으로 이동
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}

package com.haeun.sprinkler1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.haeun.com.haeun.sprinkler1.api.RetrofitClient.loginApiService
import com.example.haeun.com.haeun.sprinkler1.api.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

//        // 로그인 상태 확인
//        if (isLoggedIn()) {
//            // 이미 로그인 상태라면 MainActivity로 이동
//            val username = getSavedUsername()
//            navigateToMainActivity(username)
//            return
//        }

//        // 로그인 버튼 클릭 시
//        val loginButton: Button = findViewById(R.id.loginButton)
//        loginButton.setOnClickListener {
//            val intent = Intent(
//                Intent.ACTION_VIEW,
//                Uri.parse("https://github.com/login/oauth/authorize?client_id=Ov23lidWBtsaUstT3jPV&scope=read:user&redirect_uri=myapp://callback")
//            )
//            startActivity(intent)
//            finish()
//        }

        // 로그인 버튼 클릭 시
        val loginButton: Button = findViewById(R.id.loginButton)
        loginButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        // GitHub 계정이 없어요 버튼 클릭 시
        val noAccountButton: Button = findViewById(R.id.noAccountButton)
        noAccountButton.setOnClickListener {
            startActivity(Intent(this, GitLoginActivity::class.java))
            finish()
        }
    }


//    override fun onResume() {
//        super.onResume()
//
//        val data = intent?.data
//        Log.d("RedirectURI", "Intent data: $data")
//
//        if (data != null) {
//            Log.d("RedirectURI", "Data scheme: ${data.scheme}, Data host: ${data.host}")
//
//            if (data.scheme == "myapp" && data.host == "callback") {
//                val code = data.getQueryParameter("code")
//                Log.d("GitHubAuth", "Extracted code: $code")
//                if (!code.isNullOrEmpty()) {
//                    Log.d("GitHubAuth", "About to call requestAccessTokenAndUserInfo with code: $code")
//                    requestAccessTokenAndUserInfo(code)
//                } else {
//                    Log.e("GitHubAuth", "Authorization code not found in redirect URI")
//                }
//            } else {
//                Log.e("GitHubAuth", "Redirect URI not matched or data is null")
//            }
//        } else {
//            Log.e("GitHubAuth", "Intent data is null")
//        }
//    }
//
//    private fun requestAccessTokenAndUserInfo(code: String) {
//        val clientId = "Ov23lidWBtsaUstT3jPV"
//        val clientSecret = "23f48aa28fe14928d5e554902f915daee25074d9"
//
//        loginApiService.getAccessTokenRaw(clientId, clientSecret, code).enqueue(object : Callback<ResponseBody> {
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                Log.d("GitHubAuth", "AccessToken API Response Code: ${response.code()}")
//                if (response.isSuccessful) {
//                    val rawResponse = response.body()?.string()
//                    Log.d("GitHubAuth", "Raw AccessToken Response: $rawResponse")
//
//                    val params = rawResponse?.split("&")?.associate {
//                        val (key, value) = it.split("=")
//                        key to value
//                    }
//                    val accessToken = params?.get("access_token")
//                    Log.d("GitHubAuth", "Parsed AccessToken: $accessToken")
//
//                    if (!accessToken.isNullOrEmpty()) {
//                        saveAccessToken(accessToken)
//                        requestUserInfo(accessToken)
//                    } else {
//                        Log.e("GitHubAuth", "Access token is missing in the response")
//                    }
//                } else {
//                    val errorResponse = response.errorBody()?.string()
//                    Log.e("GitHubAuth", "Failed to retrieve access token: ${response.code()} - $errorResponse")
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                Log.e("GitHubAuth", "Failed to get access token: ${t.localizedMessage}")
//            }
//        })
//    }
//
//    private fun requestUserInfo(accessToken: String) {
//        Log.d("GitHubAuth", "Requesting user info with Authorization header: Bearer $accessToken")
//
//        loginApiService.getLoggedInUserInfo("Bearer $accessToken").enqueue(object : Callback<User> {
//            override fun onResponse(call: Call<User>, response: Response<User>) {
//                Log.d("GitHubAuth", "User info response code: ${response.code()}")
//
//                if (response.isSuccessful) {
//                    val user = response.body()
//                    Log.d("GitHubAuth", "User info raw response: $user")
//
//                    if (user != null) {
//                        Log.d("GitHubAuth", "Parsed Username: ${user.username}")
//                        saveUserInfo(accessToken, user)
//                        navigateToMainActivity(user.username)
//                    } else {
//                        Log.e("GitHubAuth", "User info is missing in response")
//                    }
//                } else {
//                    val errorResponse = response.errorBody()?.string()
//                    Log.e("GitHubAuth", "Failed to retrieve user info: ${response.code()} - $errorResponse")
//                }
//            }
//
//            override fun onFailure(call: Call<User>, t: Throwable) {
//                Log.e("GitHubAuth", "Failed to get user info: ${t.localizedMessage}")
//            }
//        })
//    }
//
//
//    private fun saveAccessToken(token: String?) {
//        if (token != null) {
//            val sharedPref = getSharedPreferences("auth", MODE_PRIVATE)
//            val editor = sharedPref.edit()
//            editor.putString("access_token", token)
//            editor.apply()
//        }
//    }
//
//    private fun isLoggedIn(): Boolean {
//        val sharedPref = getSharedPreferences("auth", MODE_PRIVATE)
//        val accessToken = sharedPref.getString("access_token", null)
//        val username = sharedPref.getString("username", null)
//        return !accessToken.isNullOrEmpty() && !username.isNullOrEmpty()
//    }
//
//    private fun saveUserInfo(token: String?, user: User?) {
//        if (token != null && user != null) {
//            val sharedPref = getSharedPreferences("auth", MODE_PRIVATE)
//            val editor = sharedPref.edit()
//            editor.putString("access_token", token)
//            editor.putString("username", user.username) // 사용자 정보 저장
//            editor.apply()
//        }
//    }
//
//    private fun navigateToMainActivity(username: String?) {
//        val intent = Intent(this, MainActivity::class.java)
//        intent.putExtra("username", username)
//        startActivity(intent)
//        finish()
//    }
//
//    private fun getSavedUsername(): String? {
//        val sharedPref = getSharedPreferences("auth", MODE_PRIVATE)
//        return sharedPref.getString("username", null)
//    }
}

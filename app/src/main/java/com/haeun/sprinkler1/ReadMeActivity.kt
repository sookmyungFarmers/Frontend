package com.haeun.sprinkler1

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.haeun.com.haeun.sprinkler1.api.ReadMe
import com.example.haeun.com.haeun.sprinkler1.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReadMeActivity : AppCompatActivity() {

    private lateinit var solveDateTextView: TextView
    private lateinit var solveTimeTextView: TextView
    private lateinit var solveStatusTextView: TextView
    private lateinit var solveDifficultyTextView: TextView
    private lateinit var commentEditText: EditText
    private lateinit var submitCommentButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_me)

        // TextView 및 EditText 초기화
        solveDateTextView = findViewById(R.id.solveDate)
        solveTimeTextView = findViewById(R.id.solveTime)
        solveStatusTextView = findViewById(R.id.solveStatus)
        solveDifficultyTextView = findViewById(R.id.solveDifficulty)
        commentEditText = findViewById(R.id.commentEditText)
        submitCommentButton = findViewById(R.id.submitCommentButton)

        val cancelButton = findViewById<Button>(R.id.cancelButton)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val viewProblemButton = findViewById<Button>(R.id.viewProblemButton)

        // Intent로 전달된 데이터
        val questionId = intent.getLongExtra("QUESTION_ID", -1L)
        val readMeId = intent.getLongExtra("README_ID", -1L)
        val userId = 1L // 로그인된 사용자 ID (임시 값)

        // 취소 버튼 이벤트 처리
        cancelButton.setOnClickListener { finish() }

        // 저장 버튼 이벤트 처리
        saveButton.setOnClickListener {
            if (readMeId == -1L) {
                createReadMe(userId, questionId)
            } else {
                updateReadMe(userId, questionId, readMeId)
            }
        }

        // 문제 보기 버튼 이벤트 처리
        viewProblemButton.setOnClickListener {
            Toast.makeText(this, "문제 보기 화면으로 이동합니다.", Toast.LENGTH_SHORT).show()
            // 문제해설 보기로 이동하는 기능 추가 가능
        }

        // 댓글 저장 버튼 이벤트 처리
        submitCommentButton.setOnClickListener {
            if (readMeId != -1L) {
                saveComment(userId, questionId, readMeId)
            } else {
                Toast.makeText(this, "리드미 ID가 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        // 서버에서 ReadMe 데이터 가져오기
        if (readMeId != -1L) {
            fetchReadMe(userId, questionId, readMeId)
        } else {
            showDefaultValues()
        }
    }

    // ReadMe 데이터 불러오기
    private fun fetchReadMe(userId: Long, questionId: Long, readMeId: Long) {
        RetrofitClient.readMeApiService.getReadMe(userId, questionId, readMeId).enqueue(object : Callback<ReadMe> {
            override fun onResponse(call: Call<ReadMe>, response: Response<ReadMe>) {
                if (response.isSuccessful) {
                    val readMe = response.body()
                    if (readMe != null) {
                        updateUI(readMe)
                    } else {
                        Toast.makeText(this@ReadMeActivity, "리드미 데이터를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
                        showDefaultValues()
                    }
                } else {
                    Log.e("ReadMeActivity", "서버 오류: ${response.errorBody()?.string()}")
                    Toast.makeText(this@ReadMeActivity, "서버 오류 발생", Toast.LENGTH_SHORT).show()
                    showDefaultValues()
                }
            }

            override fun onFailure(call: Call<ReadMe>, t: Throwable) {
                Log.e("ReadMeActivity", "네트워크 오류", t)
                Toast.makeText(this@ReadMeActivity, "네트워크 오류 발생: ${t.message}", Toast.LENGTH_SHORT).show()
                showDefaultValues()
            }
        })
    }

    // ReadMe 데이터로 UI 업데이트
    private fun updateUI(readMe: ReadMe) {
        solveDateTextView.text = "풀이일자: ${readMe.question?.date ?: "정보 없음"}"
        solveTimeTextView.text = "풀이시간: ${readMe.solution?.codeFile ?: "정보 없음"}m"
        solveStatusTextView.text = "정답여부: ${if (readMe.question?.isCorrect == true) "Pass" else "Fail"}"
        solveDifficultyTextView.text = "난이도: ${readMe.question?.difficulty ?: "정보 없음"}"
        commentEditText.setText(readMe.comment ?: "정보 없음")
    }

    // 기본값 설정
    private fun showDefaultValues() {
        solveDateTextView.text = "풀이일자: 정보 없음"
        solveTimeTextView.text = "풀이시간: 정보 없음"
        solveStatusTextView.text = "정답여부: 정보 없음"
        solveDifficultyTextView.text = "난이도: 정보 없음"
        commentEditText.setText("정보 없음")
    }

    // 새 리드미 생성
    private fun createReadMe(userId: Long, questionId: Long) {
        val newReadMe = ReadMe(
            readme_id = 0L,
            user = null,
            question = null,
            solution = null,
            comment = commentEditText.text.toString(),
            star = 0
        )

        RetrofitClient.readMeApiService.createReadMe(newReadMe).enqueue(object : Callback<ReadMe> {
            override fun onResponse(call: Call<ReadMe>, response: Response<ReadMe>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@ReadMeActivity, "리드미가 성공적으로 저장되었습니다.", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@ReadMeActivity, "리드미 저장 실패", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ReadMe>, t: Throwable) {
                Toast.makeText(this@ReadMeActivity, "네트워크 오류 발생: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // 기존 리드미 업데이트
    private fun updateReadMe(userId: Long, questionId: Long, readMeId: Long) {
        val updatedReadMe = ReadMe(
            readme_id = readMeId,
            user = null,
            question = null,
            solution = null,
            comment = commentEditText.text.toString(),
            star = 0
        )

        RetrofitClient.readMeApiService.updateReadMe(userId, questionId, readMeId, updatedReadMe).enqueue(object : Callback<ReadMe> {
            override fun onResponse(call: Call<ReadMe>, response: Response<ReadMe>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@ReadMeActivity, "리드미가 성공적으로 수정되었습니다.", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@ReadMeActivity, "리드미 수정 실패", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ReadMe>, t: Throwable) {
                Toast.makeText(this@ReadMeActivity, "네트워크 오류 발생: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // 댓글 저장
    private fun saveComment(userId: Long, questionId: Long, readMeId: Long) {
        val comment = commentEditText.text.toString()
        if (comment.isBlank()) {
            Toast.makeText(this, "댓글을 입력하세요.", Toast.LENGTH_SHORT).show()
            return
        }

        val updatedReadMe = ReadMe(
            readme_id = readMeId,
            user = null,
            question = null,
            solution = null,
            comment = comment,
            star = 0
        )

        RetrofitClient.readMeApiService.updateReadMe(userId, questionId, readMeId, updatedReadMe).enqueue(object : Callback<ReadMe> {
            override fun onResponse(call: Call<ReadMe>, response: Response<ReadMe>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@ReadMeActivity, "댓글이 성공적으로 저장되었습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@ReadMeActivity, "댓글 저장 실패", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ReadMe>, t: Throwable) {
                Toast.makeText(this@ReadMeActivity, "네트워크 오류 발생: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

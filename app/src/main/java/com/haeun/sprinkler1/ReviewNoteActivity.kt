package com.haeun.sprinkler1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.haeun.com.haeun.sprinkler1.ReviewAdapter
import com.example.haeun.com.haeun.sprinkler1.ReviewNote

class ReviewNoteActivity : AppCompatActivity() {
    private lateinit var reviewAdapter: ReviewAdapter
    private lateinit var reviewList: MutableList<ReviewNote>
    private lateinit var filteredList: MutableList<ReviewNote>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_note)

        val searchBar = findViewById<android.widget.EditText>(R.id.searchBar)
        val recyclerView = findViewById<RecyclerView>(R.id.reviewRecyclerView)

        // 예제 데이터
        reviewList = mutableListOf(
            ReviewNote("백준 #2751 - 수 정렬하기 2", "오늘"),
            ReviewNote("백준 #1111 - 수 정렬하기 1", "일주일 전"),
            ReviewNote("백준 #2222 - 수 정렬하기 3", "일주일 전"),
            ReviewNote("백준 #3333 - 수 정렬하기 4", "일주일 전")
        )

        filteredList = reviewList.toMutableList() // 초기 필터링 데이터

        // RecyclerView 설정
        reviewAdapter = ReviewAdapter(filteredList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = reviewAdapter

        // 검색 이벤트 처리
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchText = s.toString()
                filteredList.clear()
                if (searchText.isEmpty()) {
                    filteredList.addAll(reviewList)
                } else {
                    filteredList.addAll(reviewList.filter {
                        it.title.contains(searchText, ignoreCase = true)
                    })
                }
                reviewAdapter.notifyDataSetChanged()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}
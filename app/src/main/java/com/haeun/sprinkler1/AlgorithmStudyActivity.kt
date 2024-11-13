package com.haeun.sprinkler1

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class AlgorithmStudyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_algorithm_study)

        val hashButton = findViewById<TextView>(R.id.hashView)
        val queuebutton = findViewById<TextView>(R.id.queueView)
        val stackButton = findViewById<TextView>(R.id.stackView)
        val heapButton = findViewById<TextView>(R.id.heapView)
        val sortButton = findViewById<TextView>(R.id.sortView)
        val bruteForceSearchButton = findViewById<TextView>(R.id.bruteForceSearchView)
        val greedyButton = findViewById<TextView>(R.id.greedyView)
        val dynamicProgrammingButton = findViewById<TextView>(R.id.dynamicProgrammingView)
        val dfsBfsButton = findViewById<TextView>(R.id.dfsBfsView)


        hashButton.setOnClickListener {
            // Home 버튼 클릭 시 처리할 작업
            val intent = Intent(this, QuestionTimeActivity::class.java)
            startActivity(intent)
        }
        queuebutton.setOnClickListener {
            // Home 버튼 클릭 시 처리할 작업
            val intent = Intent(this, QuestionTimeActivity::class.java)
            startActivity(intent)
        }
        stackButton.setOnClickListener {
            // Home 버튼 클릭 시 처리할 작업
            val intent = Intent(this, QuestionTimeActivity::class.java)
            startActivity(intent)
        }
        heapButton.setOnClickListener {
            // Home 버튼 클릭 시 처리할 작업
            val intent = Intent(this, QuestionTimeActivity::class.java)
            startActivity(intent)
        }
        sortButton.setOnClickListener {
            // Home 버튼 클릭 시 처리할 작업
            val intent = Intent(this, QuestionTimeActivity::class.java)
            startActivity(intent)
        }
        bruteForceSearchButton.setOnClickListener {
            // Home 버튼 클릭 시 처리할 작업
            val intent = Intent(this, QuestionTimeActivity::class.java)
            startActivity(intent)
        }
        greedyButton.setOnClickListener {
            // Home 버튼 클릭 시 처리할 작업
            val intent = Intent(this, QuestionTimeActivity::class.java)
            startActivity(intent)
        }
        dynamicProgrammingButton.setOnClickListener {
            // Home 버튼 클릭 시 처리할 작업
            val intent = Intent(this, QuestionTimeActivity::class.java)
            startActivity(intent)
        }
        dfsBfsButton.setOnClickListener {
            // Home 버튼 클릭 시 처리할 작업
            val intent = Intent(this, QuestionTimeActivity::class.java)
            startActivity(intent)
        }


    }
}
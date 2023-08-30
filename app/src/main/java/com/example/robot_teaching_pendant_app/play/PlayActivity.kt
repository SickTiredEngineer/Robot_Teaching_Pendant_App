package com.example.robot_teaching_pendant_app.play

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.robot_teaching_pendant_app.R
import com.example.robot_teaching_pendant_app.databinding.PlayActivityBinding


class PlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = PlayActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
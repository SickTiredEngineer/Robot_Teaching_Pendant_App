package com.example.robot_teaching_pendant_app.make

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.robot_teaching_pendant_app.R
import com.example.robot_teaching_pendant_app.databinding.MakeActivityBinding

class MakeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = MakeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
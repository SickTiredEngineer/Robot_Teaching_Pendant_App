package com.example.robot_teaching_pendant_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.robot_teaching_pendant_app.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}
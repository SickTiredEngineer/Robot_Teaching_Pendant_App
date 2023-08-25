package com.example.robot_teaching_pendant_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.robot_teaching_pendant_app.databinding.ActivityLoginBinding
import com.example.robot_teaching_pendant_app.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val loginBt = binding.loginBt

        val loginPowerBt = binding.loginPowerBt
        val loginImage = binding.loginImage
        val loginLoadingBar = binding.loginLoadingBar


    }
}
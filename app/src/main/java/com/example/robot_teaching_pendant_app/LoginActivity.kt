package com.example.robot_teaching_pendant_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.view.View
//import android.view.WindowManager
//import android.widget.Button
import android.widget.Toast
import com.example.robot_teaching_pendant_app.databinding.LoginActivityBinding


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loginBt = binding.loginBt
        val loginPowerBt = binding.loginPowerBt
//        val loginIcon = binding.loginIcon
        val loginLoadingBar = binding.loginLoadingBar

        loginPowerBt.setOnClickListener{
            Toast.makeText(this@LoginActivity,"전원 버튼 클릭",Toast.LENGTH_SHORT).show()
        }


        // 메인 메뉴로 이동
        loginBt.setOnClickListener{
            val nextIntent = Intent(this, MainActivity::class.java)

            //시스템 변수 전송 내용 추후 추가

            startActivity(nextIntent)
            Toast.makeText(this@LoginActivity, "로그인 완료", Toast.LENGTH_SHORT ).show()
            //로그인 화면 종료
            finish()

        }

    }
}
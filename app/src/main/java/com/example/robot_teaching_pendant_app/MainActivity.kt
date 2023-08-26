package com.example.robot_teaching_pendant_app

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.robot_teaching_pendant_app.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //바인딩 설정
        val binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //상단 바 제거
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

        //Main 화면의 버튼 연결
        val mainDarkSwitch = binding.mainDarkSwitch
        val mainPowerBt = binding.mainPowerBt
        val mainMakeBt = binding.mainMakeBt
        val mainPlayBt = binding.mainPlayBt
        val mainSetupBt = binding.mainSetupBt
        val mainConnectBt = binding.mainConnectBt
        val mainDisconnectBt = binding.mainDisconnectBt

        val sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE)




        mainDarkSwitch




        //버튼 동작

        mainMakeBt.setOnClickListener{
            Toast.makeText(this@MainActivity, "작업 환경 선택 확인", Toast.LENGTH_SHORT ).show()
        }

        mainPlayBt.setOnClickListener{
            Toast.makeText(this@MainActivity, "실행 선택 확인", Toast.LENGTH_SHORT ).show()
        }

        mainSetupBt.setOnClickListener{
            Toast.makeText(this@MainActivity, "환경설정 선택 확인", Toast.LENGTH_SHORT ).show()
        }

        mainPowerBt.setOnClickListener{
            Toast.makeText(this@MainActivity,"전원 버튼 클릭",Toast.LENGTH_SHORT).show()
        }

        mainConnectBt.setOnClickListener{
            Toast.makeText(this@MainActivity,"연결 선택",Toast.LENGTH_SHORT).show()
        }

        mainDisconnectBt.setOnClickListener{
            Toast.makeText(this@MainActivity,"연결 끊기 선택",Toast.LENGTH_SHORT).show()
        }



    }
}
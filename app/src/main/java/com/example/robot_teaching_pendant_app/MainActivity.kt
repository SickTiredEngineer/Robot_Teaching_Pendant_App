package com.example.robot_teaching_pendant_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.example.robot_teaching_pendant_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //바인딩 설정
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //상단 바 제거
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)


        //Main 화면의 버튼 연결
        val mainDarkBt = binding.mainDarkBt
        val mainWhiteBt = binding.mainWhiteBt
        val mainMakeBt = binding.mainMakeBt
        val mainPlayBt = binding.mainPlayBt
        val mainSetupBt = binding.mainSetupBt


        //버튼 동작

        mainDarkBt.setOnClickListener{
            Toast.makeText(this@MainActivity, "Dark 모드 확인", Toast.LENGTH_SHORT ).show()
        }

        mainWhiteBt.setOnClickListener{
            Toast.makeText(this@MainActivity, "White 모드 확인", Toast.LENGTH_SHORT ).show()
        }

        mainMakeBt.setOnClickListener{
            Toast.makeText(this@MainActivity, "작업 환경 선택 확인", Toast.LENGTH_SHORT ).show()
        }

        mainPlayBt.setOnClickListener{
            Toast.makeText(this@MainActivity, "실행 선택 확인", Toast.LENGTH_SHORT ).show()
        }

        mainSetupBt.setOnClickListener{
            Toast.makeText(this@MainActivity, "환경설정 선택 확인", Toast.LENGTH_SHORT ).show()
        }





    }
}
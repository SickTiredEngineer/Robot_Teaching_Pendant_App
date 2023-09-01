package com.example.robot_teaching_pendant_app

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
//import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.view.View
//import android.view.Window
//import android.view.WindowManager
//import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
//import androidx.constraintlayout.widget.ConstraintLayout
import com.example.robot_teaching_pendant_app.databinding.MainActivityBinding
import com.example.robot_teaching_pendant_app.make.MakeActivity
import com.example.robot_teaching_pendant_app.play.PlayActivity
import com.example.robot_teaching_pendant_app.setup.SetupActivity


class MainActivity : AppCompatActivity() {


    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //바인딩 설정
        val binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //상단 바 제거

        //Main 화면의 버튼 연결
        val mainDarkSwitch = binding.mainDarkSwitch
        val mainPowerBt = binding.mainPowerBt
        val mainMakeBt = binding.mainMakeBt
        val mainPlayBt = binding.mainPlayBt
        val mainSetupBt = binding.mainSetupBt
        val mainConnectBt = binding.mainConnectBt
        val mainDisconnectBt = binding.mainDisconnectBt

        val sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE)
        sharedPreferences.getBoolean("night", false) //light mode is the default

        //이미 Dark 모드일 경우 Switch 를 On 합니다.
        val isNightModeOn = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
        mainDarkSwitch.isChecked = isNightModeOn


        //Dark Mode 스위치 작동 코드로, 현재 Mode에 관련된 사항을 Boolean 형식으로 SharedPreference에 저장합니다.
        mainDarkSwitch.setOnCheckedChangeListener{_, isChecked->
            if(isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                val editor = sharedPreferences.edit()
                editor.putBoolean("night",true)
                editor.apply()
            }
            else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                val editor = sharedPreferences.edit()
                editor.putBoolean("night",false)
                editor.apply()
            }
        }


        //작업 화면 버튼을 클릭 시 동작으로, 해당 화면으로 이동합니다.
        mainMakeBt.setOnClickListener{
            val nextIntent = Intent(this, MakeActivity::class.java)

            startActivity(nextIntent)
            finish()
            Toast.makeText(this@MainActivity, "작업 환경 선택 확인", Toast.LENGTH_SHORT ).show()
        }

        //실행 화면 버튼을 클릭 시 동작으로, 해당 화면으로 이동합니다.
        mainPlayBt.setOnClickListener{
            val nextIntent = Intent(this, PlayActivity::class.java)

            startActivity(nextIntent)
            finish()
            Toast.makeText(this@MainActivity, "실행 선택 확인", Toast.LENGTH_SHORT ).show()
        }

        //환경 설정 버튼을 클릭 시 동작으로, 해당 화면으로 이동합니다.
        mainSetupBt.setOnClickListener{
            val nextIntent = Intent(this, SetupActivity::class.java)

            startActivity(nextIntent)
            finish()
            Toast.makeText(this@MainActivity, "로그인 완료", Toast.LENGTH_SHORT ).show()
        }

        //우측 하단에 위치한 파워 버튼을 클릭 시 동작입니다.
        mainPowerBt.setOnClickListener{
            Toast.makeText(this@MainActivity,"전원 버튼 클릭",Toast.LENGTH_SHORT).show()
        }


        //Connect 버튼을 클릭 시 진행되는 코드입니다.
        mainConnectBt.setOnClickListener{
            Toast.makeText(this@MainActivity,"연결 선택",Toast.LENGTH_SHORT).show()
        }

        //Disconnect 버튼을 클릭 시 진행되는 코드입니다.
        mainDisconnectBt.setOnClickListener{
            Toast.makeText(this@MainActivity,"연결 끊기 선택",Toast.LENGTH_SHORT).show()
        }



    }
}
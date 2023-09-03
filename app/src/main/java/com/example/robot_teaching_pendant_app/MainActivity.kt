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
import com.example.robot_teaching_pendant_app.databinding.ConnectActivityBinding


class MainActivity : AppCompatActivity() {


    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Main Activity 바인딩
        val binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Main 화면의 버튼 연결
        val mainDarkSwitch = binding.mainDarkSwitch
        val mainPowerBt = binding.mainPowerBt
        val mainMakeBt = binding.mainMakeBt
        val mainPlayBt = binding.mainPlayBt
        val mainSetupBt = binding.mainSetupBt

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



        //Connect Activity 바인딩
        val conBinding = ConnectActivityBinding.inflate(layoutInflater)

        //연결 UI를 표시하기 위한 Layout 선언 및 Connect Activity 삽입
        val connectViewer = binding.connectViewer
        connectViewer.addView(conBinding.root)

        //Connect Activity 의 동작을 구현하는 부분입니다.
        val connectBt = conBinding.connectBt
        val disconnectBt = conBinding.disconnectBt
        disconnectBt.isEnabled = false

        connectBt.setOnClickListener{
            connectBt.isEnabled = false
            disconnectBt.setBackgroundResource(R.drawable.color_red_box)

            disconnectBt.isEnabled = true
            Toast.makeText(this@MainActivity, "Connecting Click", Toast.LENGTH_SHORT ).show()
        }

        disconnectBt.setOnClickListener{
            disconnectBt.isEnabled = false
            disconnectBt.setBackgroundResource(R.drawable.color_gray_box)

            connectBt.isEnabled = true
            Toast.makeText(this@MainActivity, "Disconnect->Enable", Toast.LENGTH_SHORT ).show()
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

    }
}
package com.example.robot_teaching_pendant_app

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
//import android.view.WindowManager
//import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.robot_teaching_pendant_app.connect.ConnectInterface
//import androidx.constraintlayout.widget.ConstraintLayout
import com.example.robot_teaching_pendant_app.databinding.MainActivityBinding
import com.example.robot_teaching_pendant_app.make.MakeActivity
import com.example.robot_teaching_pendant_app.play.PlayActivity
import com.example.robot_teaching_pendant_app.setup.SetupActivity
import com.example.robot_teaching_pendant_app.databinding.ConnectActivityBinding
import com.example.robot_teaching_pendant_app.system.ConnectHelper
import com.example.robot_teaching_pendant_app.system.DarkModeManager



class MainActivity : AppCompatActivity() {

    private lateinit var connectHelper: ConnectHelper
    private lateinit var darkModeManager:DarkModeManager

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Main Activity 바인딩
        val binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Main 화면의 버튼 연결
        val mainPowerBt = binding.mainPowerBt
        val mainMakeBt = binding.mainMakeBt
        val mainPlayBt = binding.mainPlayBt
        val mainSetupBt = binding.mainSetupBt

        //Connect를 위한 UI 동작 구현 부분 입니다.
        //연결 UI를 표시하기 위한 Layout 선언 및 Connect Activity 삽입
        val connectViewer = binding.connectViewer

        if (savedInstanceState == null) { // 처음 액티비티가 생성되었을 때만 프래그먼트 추가
            val fragment = ConnectInterface() // 여기서 YourFragment는 원하는 프래그먼트 클래스명으로 바꾸세요.
            supportFragmentManager.beginTransaction()
                .replace(connectViewer.id, fragment)
                .commit()
        }


//        //기본적인 상태를 설정합니다. 연결 상태 창은 붉은 빛이 들어오며, Disconnect 버튼은 비활성화 시킨 상태입니다.
//        conBinding.stateConBox.setBackgroundResource(R.drawable.color_red_box)
//        conBinding.disconnectBt.isEnabled = false


        /* mainDarkSwitch 버튼과 DarkModeManager 클래스를 이용하여 DarkMode 관리를 합니다. 자세한 코드는 해당 Class를 참고 하십시오*/
        darkModeManager = DarkModeManager(this, binding.mainDarkSwitch)


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
            Toast.makeText(this@MainActivity, "환경 설정 선택 확인", Toast.LENGTH_SHORT ).show()
        }

        //우측 하단에 위치한 파워 버튼을 클릭 시 동작입니다.
        mainPowerBt.setOnClickListener{
            Toast.makeText(this@MainActivity,"전원 버튼 클릭",Toast.LENGTH_SHORT).show()
        }

    }
}
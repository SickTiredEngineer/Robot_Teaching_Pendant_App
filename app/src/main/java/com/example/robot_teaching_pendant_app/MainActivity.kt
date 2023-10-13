package com.example.robot_teaching_pendant_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.view.WindowManager
//import android.widget.CompoundButton
import android.widget.Toast
import com.example.robot_teaching_pendant_app.connect.ConnectorDefaultFragment
//import androidx.constraintlayout.widget.ConstraintLayout
import com.example.robot_teaching_pendant_app.databinding.MainActivityBinding
import com.example.robot_teaching_pendant_app.make.MakeActivity
import com.example.robot_teaching_pendant_app.play.PlayActivity
import com.example.robot_teaching_pendant_app.setup.SetupActivity
import com.example.robot_teaching_pendant_app.connect.ConnectHelper
import com.example.robot_teaching_pendant_app.system.DarkModeManager
import com.example.robot_teaching_pendant_app.system.PowerOffDialogFragment


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

        if (savedInstanceState == null) { // 처음 액티비티가 생성되었을 때 프래그맨트 추가
            val fragment = ConnectorDefaultFragment()
            supportFragmentManager.beginTransaction()
                .replace(connectViewer.id, fragment)
                .commit()
        }


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
            val dialogFragment = PowerOffDialogFragment()
            dialogFragment.show(supportFragmentManager,null)

        }

    }
}
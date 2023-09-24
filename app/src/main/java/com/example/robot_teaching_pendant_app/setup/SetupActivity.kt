package com.example.robot_teaching_pendant_app.setup
import android.content.Intent
//import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.robot_teaching_pendant_app.MainActivity
import com.example.robot_teaching_pendant_app.R
import com.example.robot_teaching_pendant_app.connect.ConnectorDialogFragment
import com.example.robot_teaching_pendant_app.databinding.SetupActivityBinding
import com.example.robot_teaching_pendant_app.make.MakeActivity
import com.example.robot_teaching_pendant_app.play.PlayActivity
import com.example.robot_teaching_pendant_app.system.PowerOffFragment


class SetupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = SetupActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val setupViewer = binding.setupViewer

        //버튼 변수 등록
        val setupPowerBt = binding.setupPowerBt
        val setupConnectBt = binding.setupConnectBt

        //좌측에 위치한 설정 종류 버튼으로, 클릭 시 해당 설정을 위한 화면을 setupViewer에 출력합니다.
        val setupCobotBt = binding.setupCobotBt
        val setupToolBt = binding.setupToolBt
        val setupSystemBt = binding.setupSystemBt
        val setupLogBt = binding.setupLogBt
        val setupUtilityBt = binding.setupUtilityBt
        val setupSerialBt = binding.setupSerialBt
        val setupIo1Bt = binding.setupIo1Bt
        val setupIo2Bt = binding.setupIo2Bt
        val setupInboxBt = binding.setupInboxBt
        val setupInterfaceBt = binding.setupInterfaceBt
        val setupCoordBt = binding.setupCoordBt
        val setupSecurityBt = binding.setupSecurityBt
        val setupDevicesBt = binding.setupDevicesBt
        val setupToolListBt = binding.setupToolListBt
        val setupProgramTableBt = binding.setupProgramTableBt

        //Setup목록 버튼들을 리스트 화 하고 바로 아래에 위치한 로직에 사용합니다.
        val setupButtonList = listOf(setupCobotBt, setupToolBt, setupSystemBt, setupLogBt, setupUtilityBt, setupSerialBt, setupIo1Bt, setupIo2Bt,
            setupInboxBt, setupInterfaceBt, setupCoordBt, setupSecurityBt, setupDevicesBt, setupToolListBt, setupProgramTableBt)

        //중복 클릭 방지를 위해 자기 자신은 Disable 하고 나머지 버튼은 활성화 시켜주는 로직입니다.

        for(button in setupButtonList){
            button.setOnClickListener { clickedButton ->
                for (otherButton in setupButtonList){
                    otherButton.isEnabled = (otherButton != clickedButton)
                }
            }
        }
//        for(button in setupButtonList){
//            button.setOnClickListener { clickedButton ->
//                Log.d("ButtonClicked", "Button clicked!")
//                for (otherButton in setupButtonList){
//                    if(otherButton == clickedButton){
//                        otherButton.isEnabled = false
//                        otherButton.setBackgroundResource(R.drawable.color_red_box)
//                    }
//                    else{
//                        otherButton.isEnabled = true
//                        otherButton.setBackgroundResource(R.drawable.public_button)
//                    }
//                }
//            }
//        }

        //좌측 상단에 위치한 화면 이동 메뉴 버튼
        val setupMenuBt = binding.setupMenuBt

        //좌측 상단에 위치한 Menu 버튼 동작, Dialog 형식으로 해당 화면으로 이동할 수 있는 기능
        setupMenuBt.setOnClickListener{
            val menuDialog = AlertDialog.Builder(this)
            val menuArray = arrayOf("작업 환경", "실행", "메인 화면")

            //바깥 화면을 클릭 하여 창을 닫을 수 있습니다.
            menuDialog.setCancelable(true)
            menuDialog.setItems(menuArray) { _, p1 ->
                when (menuArray[p1]) {
                    "작업 환경" -> {
                        val nextIntent = Intent(this@SetupActivity, MakeActivity::class.java)
                        startActivity(nextIntent)
                        finish()
                    }
                    "실행" -> {
                        val nextIntent = Intent(this@SetupActivity, PlayActivity::class.java)
                        startActivity(nextIntent)
                        finish()
                    }
                    "메인 화면" -> {
                        val nextIntent = Intent(this@SetupActivity, MainActivity::class.java)
                        startActivity(nextIntent)
                        finish()
                    }
                }
            }
            menuDialog.show()
        }


        //Cobot 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupCobotBt.setOnClickListener{
            val fragment = SetupCobotFragment() // 여기서 YourFragment는 원하는 프래그먼트 클래스명으로 바꾸세요.
            supportFragmentManager.beginTransaction()
                .replace(setupViewer.id, fragment)
                .commit()
            }

        //Tool 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupToolBt.setOnClickListener{
            val fragment = SetupToolFragment() // 여기서 YourFragment는 원하는 프래그먼트 클래스명으로 바꾸세요.
            supportFragmentManager.beginTransaction()
                .replace(setupViewer.id, fragment)
                .commit()
        }

        //System 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupSystemBt.setOnClickListener{
            val fragment = SetupSystemFragment() // 여기서 YourFragment는 원하는 프래그먼트 클래스명으로 바꾸세요.
            supportFragmentManager.beginTransaction()
                .replace(setupViewer.id, fragment)
                .commit()
        }

        //Log 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupLogBt.setOnClickListener{
            val fragment =SetupLogFragment() // 여기서 YourFragment는 원하는 프래그먼트 클래스명으로 바꾸세요.
            supportFragmentManager.beginTransaction()
                .replace(setupViewer.id, fragment)
                .commit()
        }

        //Utility 버튼 클릭, 설정
        setupUtilityBt.setOnClickListener{
            val fragment =SetupUtilityFragment() // 여기서 YourFragment는 원하는 프래그먼트 클래스명으로 바꾸세요.
            supportFragmentManager.beginTransaction()
                .replace(setupViewer.id, fragment)
                .commit()
        }

        //Serial 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupSerialBt.setOnClickListener{
            val fragment = SetupSerialFragment() // 여기서 YourFragment는 원하는 프래그먼트 클래스명으로 바꾸세요.
            supportFragmentManager.beginTransaction()
                .replace(setupViewer.id, fragment)
                .commit()
        }

        //I/O1 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupIo1Bt.setOnClickListener{
            val fragment = SetupIo1Fragment() // 여기서 YourFragment는 원하는 프래그먼트 클래스명으로 바꾸세요.
            supportFragmentManager.beginTransaction()
                .replace(setupViewer.id, fragment)
                .commit()
        }

        //I/O2 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupIo2Bt.setOnClickListener{
            val fragment = SetupIo2Fragment() // 여기서 YourFragment는 원하는 프래그먼트 클래스명으로 바꾸세요.
            supportFragmentManager.beginTransaction()
                .replace(setupViewer.id, fragment)
                .commit()
        }

        //Inbox 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupInboxBt.setOnClickListener{
            val fragment = SetupInboxFragment() // 여기서 YourFragment는 원하는 프래그먼트 클래스명으로 바꾸세요.
            supportFragmentManager.beginTransaction()
                .replace(setupViewer.id, fragment)
                .commit()
        }

        //Interface 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupInterfaceBt.setOnClickListener{
            val fragment = SetupInterfaceFragment() // 여기서 YourFragment는 원하는 프래그먼트 클래스명으로 바꾸세요.
            supportFragmentManager.beginTransaction()
                .replace(setupViewer.id, fragment)
                .commit()
        }

        //Coordinate 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupCoordBt.setOnClickListener{
            val fragment = SetupCoordFragment() // 여기서 YourFragment는 원하는 프래그먼트 클래스명으로 바꾸세요.
            supportFragmentManager.beginTransaction()
                .replace(setupViewer.id, fragment)
                .commit()
        }

        //Security 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupSecurityBt.setOnClickListener{
            val fragment = SetupSecurityFragment() // 여기서 YourFragment는 원하는 프래그먼트 클래스명으로 바꾸세요.
            supportFragmentManager.beginTransaction()
                .replace(setupViewer.id, fragment)
                .commit()
        }

        //Devices 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupDevicesBt.setOnClickListener{
            val fragment = SetupDevicesFragment() // 여기서 YourFragment는 원하는 프래그먼트 클래스명으로 바꾸세요.
            supportFragmentManager.beginTransaction()
                .replace(setupViewer.id, fragment)
                .commit()
        }

        //Tool List 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupToolListBt.setOnClickListener{
            val fragment = SetupToolListFragment() // 여기서 YourFragment는 원하는 프래그먼트 클래스명으로 바꾸세요.
            supportFragmentManager.beginTransaction()
                .replace(setupViewer.id, fragment)
                .commit()
        }

        //ProgramTable 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupProgramTableBt.setOnClickListener{
            val fragment = SetupProgTableFragment() // 여기서 YourFragment는 원하는 프래그먼트 클래스명으로 바꾸세요.
            supportFragmentManager.beginTransaction()
                .replace(setupViewer.id, fragment)
                .commit()
        }


        //연결 버튼 동작입니다.
        setupConnectBt.setOnClickListener{
            val dialogFragment = ConnectorDialogFragment()
            dialogFragment.show(supportFragmentManager,null)
        }



        //우측 하단에 위치한 파워 버튼을 클릭 시 동작입니다.
        setupPowerBt.setOnClickListener{
            val dialogFragment = PowerOffFragment()
            dialogFragment.show(supportFragmentManager,null)
        }

    }
}

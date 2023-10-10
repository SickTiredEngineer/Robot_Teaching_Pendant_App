package com.example.robot_teaching_pendant_app.setup
import android.content.Intent
//import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        //각 버튼에 맞는 Fragment를 불러옵니다. 중복 클릭을 방지하기 위한 로직이 있습니다.

        for(button in setupButtonList){
            button.setOnClickListener { clickedButton ->
                // 버튼에 따라서 적절한 프래그먼트를 로드
                val fragment = when(clickedButton) {
                    setupCobotBt -> SetupCobotFragment()
                    setupToolBt -> SetupToolFragment()
                    setupSystemBt -> SetupSystemFragment()
                    setupLogBt -> SetupLogFragment()
                    setupUtilityBt -> SetupUtilityFragment()
                    setupSerialBt -> SetupSerialFragment()
                    setupIo1Bt -> SetupIo1Fragment()
                    setupIo2Bt -> SetupIo2Fragment()
                    setupInboxBt -> SetupInboxFragment()
                    setupInterfaceBt -> SetupInterfaceFragment()
                    setupCoordBt -> SetupCoordFragment()
                    setupSecurityBt -> SetupSecurityFragment()
                    setupDevicesBt -> SetupDevicesFragment()
                    setupToolListBt -> SetupToolListFragment()
                    setupProgramTableBt -> SetupProgTableFragment()
                    else -> null
                }

                fragment?.let {
                    supportFragmentManager.beginTransaction()
                        .replace(setupViewer.id, it)
                        .commit()
                }

                // 선택된 버튼은 연두색 배경이 되며 Disable, 나머지는 public_button 상태로 돌아옵니다.
                for (otherButton in setupButtonList){
                    if(otherButton == clickedButton){
                        otherButton.isEnabled = false
                        otherButton.setBackgroundResource(R.drawable.color_green_box)
                    }
                    else{
                        otherButton.isEnabled = true
                        otherButton.setBackgroundResource(R.drawable.public_button)
                    }
                }
            }
        }

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

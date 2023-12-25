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
import com.example.robot_teaching_pendant_app.system.PowerOffDialogFragment


class SetupActivity : AppCompatActivity() {

    private lateinit var binding: SetupActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = SetupActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //설정화면을 출력할 Layout의 아이디입니다.
        val setupViewer = binding.setupViewer

        //버튼 변수 등록
        val setupPowerBt = binding.setupPowerBt
        val setupConnectBt = binding.setupConnectBt

        //좌측에 위치한 설정 항목들을 불러오는 버튼으로, 클릭 시 해당 설정을 위한 Fragment 들을 setupViewer 에 출력합니다.
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

        //Setup의 좌측에 위치한, 설정 항목들을 불러오는 버튼을 리스트화 하여 로직에 사용합니다.
        val setupButtonList = listOf(setupCobotBt, setupToolBt, setupSystemBt, setupLogBt, setupUtilityBt, setupSerialBt, setupIo1Bt, setupIo2Bt,
            setupInboxBt, setupInterfaceBt, setupCoordBt, setupSecurityBt, setupDevicesBt, setupToolListBt, setupProgramTableBt)

        //각 버튼에 해당하는 Fragment를 불러오며, 중복 클릭을 방지하기 위한 로직이 있습니다.
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

                //선택된 Fragment를 Commit() 합니다.
                fragment?.let {
                    supportFragmentManager.beginTransaction()
                        .replace(setupViewer.id, it)
                        .commit()
                }

                // 선택된 버튼은 연두색 배경이 되며 중복 클릭 방지를 위해 Disable 상태가 됩니다.
                // 선택되지 않은 버튼들과, 이전에 선택되었던 버튼들은 Enable 상태가 되어 클릭할 수 있게 됩니다.
                for (otherButton in setupButtonList){

                    //클릭된 버튼은 Enable= False 상태가 되고, 초록색 배경으로 선택되었음을 나타냅니다.
                    if(otherButton == clickedButton){
                        otherButton.isEnabled = false
                        otherButton.setBackgroundResource(R.drawable.color_green_frame)
                    }

                    //클릭되지 않은 버튼들은 Enable = True 상태가 되고, 배경을 기본 버튼으로 설정합니다.(선택 되었던 버튼이 되돌려집니다.)
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

        //우측 하단에 위치한 연결 버튼을 누를 시, ConnectorDialogFragment 를 Dialog 형식으로 출력합니다.
        //자세한 내용은 Connect 디렉토리의 ConnectorDialogFragment를 참고하십시요.
        setupConnectBt.setOnClickListener{
            val dialogFragment = ConnectorDialogFragment()
            dialogFragment.show(supportFragmentManager,null)
        }

        //우측 하단에 위치한 전원 버튼을 누를 시, PowerOffDialogFragment 를 Dialog 형식으로 출력합니다.
        //자세한 내용은 System 디렉토리의 PowerOffDialogFragment를 참고하십시요.
        setupPowerBt.setOnClickListener{
            val dialogFragment = PowerOffDialogFragment()
            dialogFragment.show(supportFragmentManager,null)
        }

    }
}

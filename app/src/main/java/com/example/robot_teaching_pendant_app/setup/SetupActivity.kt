package com.example.robot_teaching_pendant_app.setup
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
//import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.robot_teaching_pendant_app.MainActivity
import com.example.robot_teaching_pendant_app.R
import com.example.robot_teaching_pendant_app.databinding.SetupActivityBinding
import com.example.robot_teaching_pendant_app.databinding.SetupCobotActivityBinding
import com.example.robot_teaching_pendant_app.databinding.SetupCoordActivityBinding
import com.example.robot_teaching_pendant_app.databinding.SetupDevicesActivityBinding
import com.example.robot_teaching_pendant_app.databinding.SetupInboxActivityBinding
import com.example.robot_teaching_pendant_app.databinding.SetupInterfaceActivityBinding
import com.example.robot_teaching_pendant_app.databinding.SetupIo1ActivityBinding
import com.example.robot_teaching_pendant_app.databinding.SetupIo2ActivityBinding
import com.example.robot_teaching_pendant_app.databinding.SetupLogActivityBinding
import com.example.robot_teaching_pendant_app.databinding.SetupProgTableActivityBinding
import com.example.robot_teaching_pendant_app.databinding.SetupSecurityActivityBinding
import com.example.robot_teaching_pendant_app.databinding.SetupSerialActivityBinding
import com.example.robot_teaching_pendant_app.databinding.SetupSystemActivityBinding
import com.example.robot_teaching_pendant_app.databinding.SetupToolActivityBinding
import com.example.robot_teaching_pendant_app.databinding.SetupToolListActivityBinding
import com.example.robot_teaching_pendant_app.databinding.SetupUtilityActivityBinding
import com.example.robot_teaching_pendant_app.make.MakeActivity
import com.example.robot_teaching_pendant_app.play.PlayActivity
import com.example.robot_teaching_pendant_app.system.FragmentPowerOff


class SetupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = SetupActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val setupViewer = binding.setupViewer

        //버튼 변수 등록
        val setupPowerBt = binding.setupPowerBt

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
            val cobotBinding = SetupCobotActivityBinding.inflate(layoutInflater)
            setupViewer.removeAllViews()
            setupViewer.addView(cobotBinding.root)

            val saveBt = cobotBinding.cobotSaveBt
            saveBt.setOnClickListener{
                Toast.makeText(this@SetupActivity,"Save 버튼 클릭",Toast.LENGTH_SHORT).show()
            }
        }

        //Tool 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupToolBt.setOnClickListener{
            val toolBinding = SetupToolActivityBinding.inflate(layoutInflater)
            setupViewer.removeAllViews()
            setupViewer.addView(toolBinding.root)
        }

        //System 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupSystemBt.setOnClickListener{
            val systemBinding = SetupSystemActivityBinding.inflate(layoutInflater)
            setupViewer.removeAllViews()
            setupViewer.addView(systemBinding.root)
        }

        //Log 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupLogBt.setOnClickListener{
            val logBinding = SetupLogActivityBinding.inflate(layoutInflater)
            setupViewer.removeAllViews()
            setupViewer.addView(logBinding.root)
        }

        //Utility 버튼 클릭, 설정
        setupUtilityBt.setOnClickListener{
            val utilBinding = SetupUtilityActivityBinding.inflate(layoutInflater)
            setupViewer.removeAllViews()
            setupViewer.addView(utilBinding.root)
        }

        //Serial 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupSerialBt.setOnClickListener{
            val serialBinding = SetupSerialActivityBinding.inflate(layoutInflater)
            setupViewer.removeAllViews()
            setupViewer.addView(serialBinding.root)
        }

        //I/O1 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupIo1Bt.setOnClickListener{
           val io1Binding = SetupIo1ActivityBinding.inflate(layoutInflater)
            setupViewer.removeAllViews()
            setupViewer.addView(io1Binding.root)
        }

        //I/O2 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupIo2Bt.setOnClickListener{
            val io2Binding = SetupIo2ActivityBinding.inflate(layoutInflater)
            setupViewer.removeAllViews()
            setupViewer.addView(io2Binding.root)
        }

        //Inbox 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupInboxBt.setOnClickListener{
            val inboxBinding = SetupInboxActivityBinding.inflate(layoutInflater)
            setupViewer.removeAllViews()
            setupViewer.addView(inboxBinding.root)
        }

        //Interface 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupInterfaceBt.setOnClickListener{
            val interBinding = SetupInterfaceActivityBinding.inflate(layoutInflater)
            setupViewer.removeAllViews()
            setupViewer.addView(interBinding.root)
        }

        //Coordinate 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupCoordBt.setOnClickListener{
            val coordBinding = SetupCoordActivityBinding.inflate(layoutInflater)
            setupViewer.removeAllViews()
            setupViewer.addView(coordBinding.root)
        }

        //Security 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupSecurityBt.setOnClickListener{
            val secuBinding = SetupSecurityActivityBinding.inflate(layoutInflater)
            setupViewer.removeAllViews()
            setupViewer.addView(secuBinding.root)
        }

        //Devices 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupDevicesBt.setOnClickListener{
            val deviceBinding = SetupDevicesActivityBinding.inflate(layoutInflater)
            setupViewer.removeAllViews()
            setupViewer.addView(deviceBinding.root)
        }

        //Tool List 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupToolListBt.setOnClickListener{
            val tlistBinding = SetupToolListActivityBinding.inflate(layoutInflater)

            setupViewer.removeAllViews()
            setupViewer.addView(tlistBinding.root)
        }

        //ProgramTable 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupProgramTableBt.setOnClickListener{
            val prgtableBinding = SetupProgTableActivityBinding.inflate(layoutInflater)
            setupViewer.removeAllViews()
            setupViewer.addView(prgtableBinding.root)
        }


        //우측 하단에 위치한 파워 버튼을 클릭 시 동작입니다.
        setupPowerBt.setOnClickListener{
            val dialogFragment = FragmentPowerOff()
            dialogFragment.show(supportFragmentManager,null)
        }

    }
}

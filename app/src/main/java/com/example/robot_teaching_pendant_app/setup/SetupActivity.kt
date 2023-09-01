package com.example.robot_teaching_pendant_app.setup
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
//import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.robot_teaching_pendant_app.MainActivity
import com.example.robot_teaching_pendant_app.R
import com.example.robot_teaching_pendant_app.databinding.SetupActivityBinding
import com.example.robot_teaching_pendant_app.make.MakeActivity
import com.example.robot_teaching_pendant_app.play.PlayActivity


class SetupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = SetupActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val setupViewer = binding.setupViewer

        //버튼 변수 등록
        val setupPowerBt = binding.setupPowerBt

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

        //좌측 상단에 위치한 Menu 버튼 동작, Dialog 형식으로 해당 화면으로 이동할 수 있는 기능
        val setupMenu = binding.setupMenuBt
        setupMenu.setOnClickListener{
            val menuDialog = AlertDialog.Builder(this)
            val menuArray = arrayOf("작업 환경", "실행", "메인 화면")
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
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflaterView = inflater.inflate(R.layout.setup_cobot_activity, setupViewer, false )
            setupViewer.removeAllViews()
            setupViewer.addView(inflaterView)
        }

        //Tool 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupToolBt.setOnClickListener{
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflaterView = inflater.inflate(R.layout.setup_tool_activity, setupViewer, false)
            setupViewer.removeAllViews()
            setupViewer.addView(inflaterView)
        }

        //System 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupSystemBt.setOnClickListener{
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflaterView = inflater.inflate(R.layout.setup_system_activity, setupViewer, false)
            setupViewer.removeAllViews()
            setupViewer.addView(inflaterView)
        }

        //Log 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupLogBt.setOnClickListener{
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflaterView = inflater.inflate(R.layout.setup_log_activity, setupViewer, false)
            setupViewer.removeAllViews()
            setupViewer.addView(inflaterView)
        }

        //Utility 버튼 클릭, 설정
        setupUtilityBt.setOnClickListener{
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflaterView = inflater.inflate(R.layout.setup_utility_activity, setupViewer, false)
            setupViewer.removeAllViews()
            setupViewer.addView(inflaterView)
        }

        //Serial 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupSerialBt.setOnClickListener{
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflaterView = inflater.inflate(R.layout.setup_serial_activity, setupViewer, false)
            setupViewer.removeAllViews()
            setupViewer.addView(inflaterView)
        }

        //I/O1 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupIo1Bt.setOnClickListener{
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflaterView = inflater.inflate(R.layout.setup_io1_activity, setupViewer, false)
            setupViewer.removeAllViews()
            setupViewer.addView(inflaterView)
        }

        //I/O2 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupIo2Bt.setOnClickListener{
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflaterView = inflater.inflate(R.layout.setup_io2_activity, setupViewer, false)
            setupViewer.removeAllViews()
            setupViewer.addView(inflaterView)
        }

        //Inbox 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupInboxBt.setOnClickListener{
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflaterView = inflater.inflate(R.layout.setup_inbox_activity, setupViewer, false)
            setupViewer.removeAllViews()
            setupViewer.addView(inflaterView)
        }

        //Interface 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupInterfaceBt.setOnClickListener{
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflaterView = inflater.inflate(R.layout.setup_interface_activity, setupViewer, false)
            setupViewer.removeAllViews()
            setupViewer.addView(inflaterView)
        }

        //Coordinate 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupCoordBt.setOnClickListener{
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflaterView = inflater.inflate(R.layout.setup_coord_activity, setupViewer, false)
            setupViewer.removeAllViews()
            setupViewer.addView(inflaterView)
        }

        //Security 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupSecurityBt.setOnClickListener{
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflaterView = inflater.inflate(R.layout.setup_security_activity, setupViewer, false)
            setupViewer.removeAllViews()
            setupViewer.addView(inflaterView)
        }

        //Devices 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupDevicesBt.setOnClickListener{
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflaterView = inflater.inflate(R.layout.setup_devices_activity, setupViewer, false)
            setupViewer.removeAllViews()
            setupViewer.addView(inflaterView)
        }

        //Tool List 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupToolListBt.setOnClickListener{
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflaterView = inflater.inflate(R.layout.setup_tool_list_activity, setupViewer, false)
            setupViewer.removeAllViews()
            setupViewer.addView(inflaterView)
        }

        //ProgramTable 버튼 클릭 시, 해당 사항에 관한 설정을 진행할 수 있는 창이 우측에 생성됨
        setupProgramTableBt.setOnClickListener{
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflaterView = inflater.inflate(R.layout.setup_prog_table_activity, setupViewer, false)
            setupViewer.removeAllViews()
            setupViewer.addView(inflaterView)
        }


        //우측 하단에 위치한 파워 버튼을 클릭 시 동작입니다.
        setupPowerBt.setOnClickListener{
            Toast.makeText(this@SetupActivity,"전원 버튼 클릭", Toast.LENGTH_SHORT).show()
        }


    }
}

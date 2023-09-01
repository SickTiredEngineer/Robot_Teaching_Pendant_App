package com.example.robot_teaching_pendant_app.setup
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
//import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
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

        //좌측 상단 메뉴 버튼
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

        //Cobot 버튼 클릭, 설정
        setupCobotBt.setOnClickListener{
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflaterView = inflater.inflate(R.layout.setup_cobot_activity, setupViewer, false )
            setupViewer.removeAllViews()
            setupViewer.addView(inflaterView)
        }

        //Tool 버튼 클릭, 설정
        setupToolBt.setOnClickListener{
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflaterView = inflater.inflate(R.layout.setup_tool_activity, setupViewer, false)
            setupViewer.removeAllViews()
            setupViewer.addView(inflaterView)
        }

        //System 버튼 클릭, 설정
        setupSystemBt.setOnClickListener{
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflaterView = inflater.inflate(R.layout.setup_system_activity, setupViewer, false)
            setupViewer.removeAllViews()
            setupViewer.addView(inflaterView)
        }

        //Log 버튼 클릭, 설정
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

        //Serial 버튼 클릭, 설정
        setupSerialBt.setOnClickListener{
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflaterView = inflater.inflate(R.layout.setup_serial_activity, setupViewer, false)
            setupViewer.removeAllViews()
            setupViewer.addView(inflaterView)
        }

        //I/O1 버튼 클릭, 설정
        setupIo1Bt.setOnClickListener{
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflaterView = inflater.inflate(R.layout.setup_io1_activity, setupViewer, false)
            setupViewer.removeAllViews()
            setupViewer.addView(inflaterView)
        }

        //I/O2 버튼 클릭, 설정
        setupIo2Bt.setOnClickListener{
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflaterView = inflater.inflate(R.layout.setup_io2_activity, setupViewer, false)
            setupViewer.removeAllViews()
            setupViewer.addView(inflaterView)
        }

        //Inbox 버튼 클릭, 설정
        setupInboxBt.setOnClickListener{
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflaterView = inflater.inflate(R.layout.setup_inbox_activity, setupViewer, false)
            setupViewer.removeAllViews()
            setupViewer.addView(inflaterView)
        }

        //Interface 버튼 클릭, 설정
        setupInterfaceBt.setOnClickListener{
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflaterView = inflater.inflate(R.layout.setup_interface_activity, setupViewer, false)
            setupViewer.removeAllViews()
            setupViewer.addView(inflaterView)
        }

        //Coordinate 버튼 클릭, 설정
        setupCoordBt.setOnClickListener{
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflaterView = inflater.inflate(R.layout.setup_coord_activity, setupViewer, false)
            setupViewer.removeAllViews()
            setupViewer.addView(inflaterView)
        }

        //Security 버튼 클릭, 설정
        setupSecurityBt.setOnClickListener{
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflaterView = inflater.inflate(R.layout.setup_security_activity, setupViewer, false)
            setupViewer.removeAllViews()
            setupViewer.addView(inflaterView)
        }

        //Devices 버튼 클릭, 설정
        setupDevicesBt.setOnClickListener{
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflaterView = inflater.inflate(R.layout.setup_devices_activity, setupViewer, false)
            setupViewer.removeAllViews()
            setupViewer.addView(inflaterView)
        }

        //Tool List 버튼 클릭, 설정
        setupToolListBt.setOnClickListener{
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflaterView = inflater.inflate(R.layout.setup_tool_list_activity, setupViewer, false)
            setupViewer.removeAllViews()
            setupViewer.addView(inflaterView)
        }

        //ProgramTable 버튼 클릭, 설정
        setupProgramTableBt.setOnClickListener{
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflaterView = inflater.inflate(R.layout.setup_prog_table_activity, setupViewer, false)
            setupViewer.removeAllViews()
            setupViewer.addView(inflaterView)
        }


    }
}

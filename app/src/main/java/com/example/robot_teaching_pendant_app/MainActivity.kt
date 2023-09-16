package com.example.robot_teaching_pendant_app

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
//import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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

        //Connect를 위한 UI 동작 구현 부분 입니다.
        //연결 UI를 표시하기 위한 Layout 선언 및 Connect Activity 삽입
        val conBinding = ConnectActivityBinding.inflate(layoutInflater)
        val connectViewer = binding.connectViewer
        connectViewer.addView(conBinding.root)

        /* conCount 변수는 현재 연결 진행 단계를 나타냅니다. 이 변수를 가준으로 UI 동작을 수행하게 됩니다.
        연걸전 Disconnect 버튼은 FALSE 상태로 맞춘 후 , 연결이 되면 true로 변경합니다. */
        var conCount: Int = 0
        val connectBt = conBinding.connectBt

        val disconnectBt = conBinding.disconnectBt
        disconnectBt.isEnabled = false

        //연결 상태를 문자로 표시해주는 Textview 바인딩
        val stateConnect = conBinding.stateConnect
        val statePower = conBinding.statePower
        val stateDevice = conBinding.stateDevice
        val stateSystem = conBinding.stateSystem
        val stateRobOper = conBinding.stateRobotOperation

        //연결 상태를 색으로 표현해주는 TextView 바인딩
        val stateConBox = conBinding.stateConBox
        stateConBox.setBackgroundResource(R.drawable.color_red_box)

        val statePowerBox = conBinding.statePowerBox
        val stateDeviceBox = conBinding.stateDeviceBox
        val stateSystemBox = conBinding.stateSystemBox
        val stateRobOperBox = conBinding.stateRobOperBox


        //Night Mode 구현 부분
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



        //Connect 버튼 동작 설정, conCount 변수에 있는 카운트 값을 기반으로 동작이 분기합니다.
        connectBt.setOnClickListener{
            when(conCount){
                // 첫 번째 동작 (Connect) -> Connect 를 진행하며 동작이 끝나면 Connect 버튼을 Control 로 변환합니다.
                0 -> {
                    //중복 클릭을 방지하기 위해 과정이 끝나기 전 까지 connect 버튼을 비활성화 합니다.
                    connectBt.isEnabled = false

                    //첫번째 클릭 시, Connect state 의 텍스트와 상태 상자 색깔을 변경합니다.
                    stateConnect.setText(R.string.state_connecting)
                    stateConBox.setBackgroundResource(R.drawable.color_yellow_box)

                    //연결 과정 표현 (딜레이 이용)
                    val mHandler = Handler(Looper.getMainLooper())
                    mHandler.postDelayed({
                        stateConnect.setText(R.string.state_connected)
                        stateConBox.setBackgroundResource(R.drawable.color_green_box)

                        connectBt.setText(R.string.str_control)
                        disconnectBt.setBackgroundResource(R.drawable.color_red_box)
                        disconnectBt.isEnabled = true

                        //Connect 버튼을 다시 활성화 시킵니다.
                        connectBt.isEnabled = true

                    }, 3000L)
                    conCount += 1
                }

                //두 번째 동작 (Control) -> 나머지 요소들에 대한 작업을 진행하고 동작이 끝나면 Connect 버튼을 Power Down 으로 변경합니다.
                1 -> {
                    //중복 클릭을 방지하기 위해 과정이 끝나기 전 까지 connect 버튼을 비활성화 합니다.
                    connectBt.isEnabled = false

                    val mHandler = Handler(Looper.getMainLooper())
                    mHandler.postDelayed({
                        statePower.setText(R.string.state_power_on)
                        statePowerBox.setBackgroundResource(R.drawable.color_green_box)
                    }, 1000L)

                    mHandler.postDelayed({
                        stateDevice.setText(R.string.state_device_on)
                        stateDeviceBox.setBackgroundResource(R.drawable.color_green_box)
                    }, 1500L)

                    mHandler.postDelayed({
                        stateSystem.setText(R.string.state_system_on)
                        stateSystemBox.setBackgroundResource(R.drawable.color_green_box)
                    }, 2000L)

                    mHandler.postDelayed({
                        stateRobOperBox.setBackgroundResource(R.drawable.color_green_box)
                    }, 2500L)

                    mHandler.postDelayed({
                        connectBt.setText(R.string.str_power_down)
                        connectBt.setBackgroundResource(R.drawable.color_yellow_box)

                        //Connect 버튼을 다시 활성화 시킵니다.
                        connectBt.isEnabled = true

                    }, 3000L)
                    conCount += 1
                }

                //세 번째 동작(Power Down) -> 초기 상태로 연결 UI를 되돌립니다.
                2 -> {
                    stateConnect.setText(R.string.state_disconnected)
                    stateConBox.setBackgroundResource(R.drawable.color_red_box)

                    statePower.setText(R.string.state_power_off)
                    statePowerBox.setBackgroundResource(R.drawable.square_background_border)

                    stateDevice.setText(R.string.state_device_off)
                    stateDeviceBox.setBackgroundResource(R.drawable.square_background_border)

                    stateSystem.setText(R.string.state_system_off)
                    stateSystemBox.setBackgroundResource(R.drawable.square_background_border)

                    stateRobOperBox.setBackgroundResource(R.drawable.square_background_border)

                    connectBt.setText(R.string.str_connect)
                    connectBt.setBackgroundResource(R.drawable.color_green_box)

                    disconnectBt.setBackgroundResource(R.drawable.color_gray_box)
                    disconnectBt.isEnabled = false
                    conCount = 0
                }
            }
        }

        //연결 끊기 버튼은 conCount를 즉시 0으로 초기화 시키고 연결 UI를 초기 상태로 돌려놓습니다.
        disconnectBt.setOnClickListener{

            stateConnect.setText(R.string.state_disconnected)
            stateConBox.setBackgroundResource(R.drawable.color_red_box)

            statePower.setText(R.string.state_power_off)
            statePowerBox.setBackgroundResource(R.drawable.square_background_border)

            stateDevice.setText(R.string.state_device_off)
            stateDeviceBox.setBackgroundResource(R.drawable.square_background_border)

            stateSystem.setText(R.string.state_system_off)
            stateSystemBox.setBackgroundResource(R.drawable.square_background_border)

            stateRobOperBox.setBackgroundResource(R.drawable.square_background_border)

            connectBt.setText(R.string.str_connect)
            connectBt.setBackgroundResource(R.drawable.color_green_box)

            disconnectBt.isEnabled = false
            disconnectBt.setBackgroundResource(R.drawable.color_gray_box)

            conCount = 0
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
            Toast.makeText(this@MainActivity, "환경 설정 선택 확인", Toast.LENGTH_SHORT ).show()
        }

        //우측 하단에 위치한 파워 버튼을 클릭 시 동작입니다.
        mainPowerBt.setOnClickListener{
            Toast.makeText(this@MainActivity,"전원 버튼 클릭",Toast.LENGTH_SHORT).show()
        }

    }
}
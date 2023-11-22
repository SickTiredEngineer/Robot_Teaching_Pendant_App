package com.example.robot_teaching_pendant_app.connect

import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.robot_teaching_pendant_app.R

//생성자입니다. 나중에 필요 시, 추가적으로 제어상자의 정보 View 등을 추가해야 합니다.
class ConnectHelper(

    //ConnectorFragment 에서 사용 될 생성자 파라미터
    private val connectBt: Button,
    private val disconnectBt: Button,
    private val stateConnect: TextView,
    private val stateConBox: View,
    private val stateRobOperBox: View
) {


    init {
        setConnectBtOnClickListener()
        setDisconnectBtOnClickListener()

        //connect 디렉토리에 위치한 Object ConnectionState 에 있는 변수 ConnectionState.conCount 에 초기화 된 값에 따라 UI의 초기 상태를 설정합니다.
        when(ConnectionState.conCount){
            0 -> {
                setConUiDef()
            }
            1-> {
                setConUiFirst()
            }
            2-> {
                setConUiSecond()
            }

        }

    }

    //연결 단계를 나타내는 변수입니다.
    //추가로 연결 단계를 나타내는 변수들이 아직 숫자를 사용하고 있습니다. 어느정도 시스템이 구현되면 변수(상수)이름을 이용하여 단계를 표시하고자 합니다.
    private var conCount = 0

    //connect 버튼의 클릭 리스너 동작입니다. Case는 아래에 구현해 놓았습니다.
    private fun setConnectBtOnClickListener() {
        connectBt.setOnClickListener {
            when(conCount){
                0 -> FirstClick()
                1 -> SecondClick()
                2 -> ThirdClick()
            }
        }
    }

    //Disconnect 버튼의 클릭 리스너 동작입니다. 별도의 Case는 없습니다.
    private fun setDisconnectBtOnClickListener() {
        disconnectBt.setOnClickListener {
            disconnectClick()
        }
    }


    /* Connect 버튼을 클릭 했을 시,  conCount 변수의 값에 따라 단계별로 실행되는 코드입니다. 현재 딜레이 표현을 위한
    Handler를 이용하여 메인 스레드에서 실행하지만, 이 후 연결과 함께 UI 동작을 구현할 때 runOnUiThread를 사용해야 합니다.*/

    //추가로 연결 단계를 나타내는 변수들이 아직 숫자를 사용하고 있습니다. 어느정도 시스템이 구현되면 변수(상수)이름을 이용하여 단계를 표시하고자 합니다.
    private fun FirstClick() {
        //중복 클릭을 방지하기 위해 과정이 끝나기 전 까지 connect 버튼을 비활성화 합니다.
        connectBt.isEnabled = false

        //첫번째 클릭 시, Connect state 의 텍스트와 상태 상자 색깔을 변경합니다.
        stateConnect.setText(R.string.state_connecting)
        stateConBox.setBackgroundResource(R.drawable.color_yellow_frame)

        //연결 과정 표현 (딜레이 이용)
        val mHandler = android.os.Handler(Looper.getMainLooper())
        mHandler.postDelayed({
            stateConnect.setText(R.string.state_connected)
            stateConBox.setBackgroundResource(R.drawable.color_green_frame)

            connectBt.setText(R.string.str_control)
            disconnectBt.setBackgroundResource(R.drawable.color_red_frame)

            //Connect,Disconnect 버튼을 다시 활성화 시킵니다.
            connectBt.isEnabled = true
            disconnectBt.isEnabled = true
        }, 3000L)
        conCount = 1
        ConnectionState.conCount = 1
    }

    private fun SecondClick() {
        //중복 클릭을 방지하기 위해 과정이 끝나기 전 까지 connect, Disconnect 버튼을 비활성화 합니다.
        connectBt.isEnabled = false
        disconnectBt.isEnabled = false

        val mHandler = Handler(Looper.getMainLooper())


        mHandler.postDelayed({
            stateRobOperBox.setBackgroundResource(R.drawable.color_green_frame)
        }, 2500L)

        mHandler.postDelayed({
            connectBt.setText(R.string.str_power_down)
            connectBt.setBackgroundResource(R.drawable.color_yellow_frame)

            //Connect,Disconnect 버튼을 다시 활성화 시킵니다.
            connectBt.isEnabled = true
            disconnectBt.isEnabled = true
        }, 3000L)
        conCount = 2
        ConnectionState.conCount = 2

    }
    private fun ThirdClick() {

        stateConnect.setText(R.string.state_disconnected)
        stateConBox.setBackgroundResource(R.drawable.color_red_frame)

        stateRobOperBox.setBackgroundResource(R.drawable.main_frame)

        connectBt.setText(R.string.str_connect)
        connectBt.setBackgroundResource(R.drawable.color_green_frame)

        disconnectBt.setBackgroundResource(R.drawable.color_gray_frame)
        disconnectBt.isEnabled = false
        conCount = 0
        ConnectionState.conCount = 0

    }

    private fun disconnectClick(){

        stateConnect.setText(R.string.state_disconnected)
        stateConBox.setBackgroundResource(R.drawable.color_red_frame)

        stateRobOperBox.setBackgroundResource(R.drawable.main_frame)

        connectBt.setText(R.string.str_connect)
        connectBt.setBackgroundResource(R.drawable.color_green_frame)

        disconnectBt.setBackgroundResource(R.drawable.color_gray_frame)
        disconnectBt.isEnabled = false
        conCount = 0
        ConnectionState.conCount = 0
    }

    private fun setConUiDef(){
        conCount = 0
        stateConBox.setBackgroundResource(R.drawable.color_red_frame)
        stateConnect.setText(R.string.state_disconnected)
    }

    private fun setConUiFirst(){
        conCount = 1
        connectBt.setText(R.string.str_control)
        disconnectBt.setBackgroundResource(R.drawable.color_red_frame)
        disconnectBt.isEnabled = true

        stateConBox.setBackgroundResource(R.drawable.color_green_frame)
        stateConnect.setText(R.string.state_connected)
    }
    private fun setConUiSecond(){
        conCount = 2
        connectBt.setBackgroundResource(R.drawable.color_yellow_frame)
        connectBt.setText(R.string.str_power_down)
        disconnectBt.setBackgroundResource(R.drawable.color_red_frame)

        stateConBox.setBackgroundResource(R.drawable.color_green_frame)

        stateRobOperBox.setBackgroundResource(R.drawable.color_green_frame)

        stateConnect.setText(R.string.state_connected)

    }

}





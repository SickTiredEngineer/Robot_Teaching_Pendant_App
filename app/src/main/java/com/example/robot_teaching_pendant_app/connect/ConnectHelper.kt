package com.example.robot_teaching_pendant_app.connect

import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.robot_teaching_pendant_app.R

//생성자입니다. 나중에 필요 시, 추가적으로 제어상자의 정보 View 등을 추가해야 합니다.
//근데 굳이 이렇게 나누어서 할 필요가 있었을까...? -> 나중에 그냥 현재 파일 삭제하고 ConnectorFragment에 옮기는것 고려해보기. 2023/11/23

class ConnectHelper(
    //ConnectorFragment에서 해당 Class를 이용하기 위한 생성자 인자들입니다.
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

            //ConnectionState.conCount가 0단계일때, 해당 단계에서의 UI를 세팅을 하는 함수를 호출합니다.
            0 -> {
                setConUiDef()
            }
            //ConnectionState.conCount가 1단계일때, 해당 단계에서의 UI를 세팅을 하는 함수를 호출합니다.
            1-> {
                setConUiFirst()
            }
            //ConnectionState.conCount가 2단계일때, 해당 단계에서의 UI를 세팅을 하는 함수를 호출합니다.
            2-> {
                setConUiSecond()
            }
        }
    }

    //연결 단계를 나타내는 변수입니다.
    //추가로 연결 단계를 나타내는 변수들이 아직 숫자를 사용하고 있습니다. 어느정도 시스템이 구현되면 변수(상수)이름을 이용하여 단계를 표시하고자 합니다.
    private var conCount = 0

    //connect 버튼의 클릭 리스너 동작입니다.
    //Connect 버튼의 클릭 횟수에 따라(단계에 따라) UI가 동작합니다.
    private fun setConnectBtOnClickListener() {
        connectBt.setOnClickListener {
            when(conCount){
                //처음 클릭 시 동작입니다.
                0 -> FirstClick()
                //두 번째 클릭 시 동작입니다.
                1 -> SecondClick()
                //세 번쨰 클릭 시 동작입니다.
                2 -> ThirdClick()
            }
        }
    }

    //Disconnect 버튼의 클릭 리스너로 disconnectClick() 함수를 호출하여, UI를 연결 전의 초기 상태로 되돌립니다.
    private fun setDisconnectBtOnClickListener() {
        disconnectBt.setOnClickListener {
            disconnectClick()
        }
    }


    /* Connect 버튼을 클릭 했을 시,  conCount 변수의 값에 따라 단계별로 실행되는 코드입니다. 현재 딜레이 표현을 위한
    Handler를 이용하여 메인 스레드에서 실행하지만, 이 후 연결과 함께 UI 동작을 구현할 때 runOnUiThread를 사용해야 합니다.*/

    //추가로 연결 단계를 나타내는 변수들이 아직 숫자를 사용하고 있습니다. 어느정도 시스템이 구현되면 변수(상수)이름을 이용하여 단계를 표시하고자 합니다.
    //연결을 표시하는 목록은 총 2가지로, Network 와 Robot Operation 입니다.
    //추가적으로 연결과정은 더 추가될수도 있습니다.


    //Connect버튼을 처음 클릭했을 시 동작입니다.
    private fun FirstClick() {
        //중복 클릭을 방지하기 위해, 연결 과정이 끝나기 전 까지 connect 버튼을 비활성화 합니다.
        connectBt.isEnabled = false

        //Connect State 의 Text에 연결을 진행하고 있음을 표시합니다.
        stateConnect.setText(R.string.state_connecting)
        //Connect State의 상태를 나타내는 상자의 배경을 노란색으로 변경합니다.
        stateConBox.setBackgroundResource(R.drawable.color_yellow_frame)

        //연결 과정을 Handler의 Delay를 이용하여 임시로 표현했습니다.
        val mHandler = android.os.Handler(Looper.getMainLooper())
        mHandler.postDelayed({
            //Connect의 상태를 나타내는 TextView에 연결이 되었음을 표시합니다.
            stateConnect.setText(R.string.state_connected)
            //Connect의 상태를 나타내는 상자의 배경을 초록색으로 변경합니다.
            stateConBox.setBackgroundResource(R.drawable.color_green_frame)

            //Connect버튼의 Text를 Control로 변경합니다. 해당 부분은 이후 수정될 수 있습니다.
            connectBt.setText(R.string.str_control)
            //Disconnect 버튼을 클릭할 수 있음을 표시하기 위해 배경색을 붉은색으로 변경합니다.
            disconnectBt.setBackgroundResource(R.drawable.color_red_frame)

            //동작이 끝나면 Connect,Disconnect 버튼을 활성화 시킵니다.
            connectBt.isEnabled = true
            disconnectBt.isEnabled = true

            //딜레이시간: 2000L
        }, 2000L)

        //연결 단계를 표현하는 변수에 현재 단계(1)를 대입합니다.
        conCount = 1
        ConnectionState.conCount = 1
    }

    //Connect버튼을 두 번째 클릭했을 시 동작입니다.
    private fun SecondClick() {
        //중복 클릭을 방지하기 위해, 연결 과정이 끝나기 전 까지 connect 버튼을 비활성화 합니다.
        connectBt.isEnabled = false
        disconnectBt.isEnabled = false

        val mHandler = Handler(Looper.getMainLooper())

        //연결 과정을 Handler의 Delay를 이용하여 임시로 표현했습니다.
        mHandler.postDelayed({
            //Robot Operation의 상태를 나타내는 상자의 배경을 초록색으로 변경합니다.
            stateRobOperBox.setBackgroundResource(R.drawable.color_green_frame)

            //딜레이는 2000L 입니다.
        }, 2000L)

        mHandler.postDelayed({
            //Connect버튼의 Text를 Power_Down 으로 변경합니다. 해당 부분은 이후 수정될 수 있습니다.
            connectBt.setText(R.string.str_power_down)
            //Connect 버튼이 Shutdown 버튼임을 알 수 있도록 배경 색을 노란색으로 변경합니다.
            connectBt.setBackgroundResource(R.drawable.color_yellow_frame)

            //Connect,Disconnect 버튼을 다시 활성화 시킵니다.
            connectBt.isEnabled = true
            disconnectBt.isEnabled = true

            //딜레이는 2500L 입니다.
        }, 2500L)

        //연결 단계를 표현하는 변수에 현재 단계(2)를 대입합니다.
        conCount = 2
        ConnectionState.conCount = 2

    }

    //Connect버튼을 세 번째 클릭했을 시 동작입니다.
    private fun ThirdClick() {
        //연결 Ui의 모든 요소들을 초기 상태로 되돌립니다.

        disconnectClick()

//        stateConnect.setText(R.string.state_disconnected)
//        stateConBox.setBackgroundResource(R.drawable.color_red_frame)
//
//        stateRobOperBox.setBackgroundResource(R.drawable.main_frame)
//
//        connectBt.setText(R.string.str_connect)
//        connectBt.setBackgroundResource(R.drawable.color_green_frame)
//
//        disconnectBt.setBackgroundResource(R.drawable.color_gray_frame)
//        disconnectBt.isEnabled = false
//
//        //연결 단계를 표현하는 변수에 현재 단계(0)를 대입합니다.
//        //즉 연결 단계를 초기화합니다.
//        conCount = 0
//        ConnectionState.conCount = 0

    }


    //Disconnect 버튼을 클릭했을 시 호출되는 함수로, UI를 초기 상태로 되돌립니다.
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


    //연결 단계가 0단계인 경우의 연결 UI를 설정합니다.
    private fun setConUiDef(){
        conCount = 0
        stateConBox.setBackgroundResource(R.drawable.color_red_frame)
        stateConnect.setText(R.string.state_disconnected)
    }

    //연결 단계가 1단계인 경우의 연결 UI를 설정합니다.
    private fun setConUiFirst(){
        conCount = 1
        connectBt.setText(R.string.str_control)
        disconnectBt.setBackgroundResource(R.drawable.color_red_frame)
        disconnectBt.isEnabled = true

        stateConBox.setBackgroundResource(R.drawable.color_green_frame)
        stateConnect.setText(R.string.state_connected)
    }

    //연결 단계가 2단계인 경우의 연결 UI를 설정합니다.
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





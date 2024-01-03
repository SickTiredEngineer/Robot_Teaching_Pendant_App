package com.example.robot_teaching_pendant_app.system

object JogState {

    //Jog 의 어떤 좌표계를 골랐는 지 표현하는 전역 변수 입니다.
    var jogSelected: Int = 0

    const val JOG_GLOBAL_SELECTED: Int = 0
    const val JOG_LOCAL_SELECTED: Int = 1
    const val JOG_USER_SELECTED: Int = 2
    const val JOG_JOINT_SELECTED: Int = 3



    var jogModeSelected: Int = 0

    const val JOG_SMOOTH_SELECTED: Int = 0
    const val JOG_TICK_SELECTED: Int =1


    var jogModeDist: Float = 0.0f;
    var jogModeOri: Float = 0.0f;
    var jogModeJoint: Float = 0.0f;


}
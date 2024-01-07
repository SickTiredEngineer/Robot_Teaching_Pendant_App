package com.example.robot_teaching_pendant_app.system

object JogState {

    /**
     선택된 조그의 좌표계나 Jogmode (Smooth, Thick) 의 상태를 저장 하는 Object 입니다.
     관련된 로직은 JogFragment 를 확인 하십시오.
     */



    /**
    ===================================================================================================================================
    아래의 변수들은 선택된 좌표계를 저장하는 변수들 입니다.
     */

    //Jog 의 어떤 좌표계를 골랐는 지 표현하는 전역 변수 입니다.
    var jogSelected: Int = 0

    const val JOG_GLOBAL_SELECTED: Int = 0
    const val JOG_LOCAL_SELECTED: Int = 1
    const val JOG_USER_SELECTED: Int = 2
    const val JOG_JOINT_SELECTED: Int = 3


    /**
    ===================================================================================================================================
    아래의 변수들은 Jog Mode 상태를 저장하는 변수들 입니다.
     */

    var jogModeSelected: Int = 0

    const val JOG_SMOOTH_SELECTED: Int = 0
    const val JOG_TICK_SELECTED: Int =1


    var jogModeDist: Float = 0.0f;
    var jogModeOri: Float = 0.0f;
    var jogModeJoint: Float = 0.0f;


}
package com.example.robot_teaching_pendant_app.system

object JogState {

    //Jog 의 어떤 좌표계를 골랐는 지 표현하는 전역 변수 입니다.
    const val JOG_GLOBAL_SELECTED: Int = 0
    const val JOG_LOCAL_SELECTED: Int = 1
    const val JOG_USER_SELECTED: Int = 2
    const val JOG_JOINT_SELECTED: Int = 3
    var jogSelected: Int = 0


    const val JOG_SMOOTH_SELECTED: Int = 0
    const val JOG_TICK_SELECTED: Int =1

    var jogModeSelected: Int = 0


//    //Global 좌표계 값들을 초기화 하는 변수들 입니다.
//    var globalX: Float = 0f
//    var globalY: Float = 0f
//    var globalZ: Float = 0f
//    var globalRx: Float = 0f
//    var globalYRy: Float = 0f
//    var globalYRz: Float = 0f
//
//    //Local 좌표계 값들을 초기화 하는 변수들 입니다.
//    var localX: Float = 0f
//    var localY: Float = 0f
//    var localZ: Float = 0f
//    var localRx: Float = 0f
//    var localRy: Float = 0f
//    var localRz: Float = 0f
//
//    //User 좌표계 값들을 초기화 하는 변수들입니다. 맨 뒤의 숫자는 각 유저 좌표계 1번, 2번, 3번을 뜻 합니다.
//    var userX1: Float = 0f
//    var userY1: Float = 0f
//    var userZ1: Float = 0f
//    var userRx1: Float = 0f
//    var userRy1: Float = 0f
//    var userRz1: Float = 0f
//
//    var userX2: Float = 0f
//    var userY2: Float = 0f
//    var userZ2: Float = 0f
//    var userRx2: Float = 0f
//    var userRy2: Float = 0f
//    var userRz2: Float = 0f
//
//    var userX3: Float = 0f
//    var userY3: Float = 0f
//    var userZ3: Float = 0f
//    var userRx3: Float = 0f
//    var userRy3: Float = 0f
//    var userRz3: Float = 0f
//
//    //4개 관절(Joint) 값들을 초기화 하는 변수 입니다.
//    var joint1: Float = 0f
//    var joint2: Float = 0f
//    var joint3: Float = 0f
//    var joint4: Float = 0f
}
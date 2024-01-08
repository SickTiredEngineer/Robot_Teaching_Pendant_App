package com.example.robot_teaching_pendant_app.system

object RobotPosition {

    /**
    로봇의 현재 위치 정보를 저장하는 Object 입니다.
     설계 과정에 따라 변수가 추가될 수 있습니다.
     */

    //Global 좌표계의 좌표 값을 저장하는 변수 6개입니다.
    var global_x: Float = 0.0f
    var global_y: Float = 0.0f
    var global_z: Float = 0.0f
    var global_Rx: Float = 0.0f
    var global_Ry: Float = 0.0f
    var global_Rz: Float = 0.0f

    //Local 좌표계의 좌표 값을 저장하는 변수 6개입니다.
    var local_x: Float = 0.0f
    var local_y: Float = 0.0f
    var local_z: Float = 0.0f
    var local_Rx: Float = 0.0f
    var local_Ry: Float = 0.0f
    var local_Rz: Float = 0.0f

    //User 좌표계의 좌표 값을 저장하는 변수 6개입니다.
    var user_x: Float = 0.0f
    var user_y: Float = 0.0f
    var user_z: Float = 0.0f
    var user_Rx: Float = 0.0f
    var user_Ry: Float = 0.0f
    var user_Rz: Float = 0.0f

    //관절 값을 저장하는 변수 4개입니다.
    var joint1: Float = 0.0f
    var joint2: Float = 0.0f
    var joint3: Float = 0.0f
    var joint4: Float = 0.0f

    /**
    ===================================================================================================================================
    아래 변수들은 서버로부터 받은 좌표계 값을 저장합니다. 서버가 보내오는 값의 양에 따라서 갯수가 추가될 수 있습니다.
     */

    var xFromServer: Float = 0.0f
    var yFromServer: Float = 0.0f
    var zFromServer: Float = 0.0f
    var rxFromServer: Float = 0.0f
    var ryFromServer: Float = 0.0f
    var rzFromServer: Float = 0.0f

    var joint1FromServer: Float = 0.0f
    var joint2FromServer: Float = 0.0f
    var joint3FromServer: Float = 0.0f
    var joint4FromServer: Float = 0.0f

}
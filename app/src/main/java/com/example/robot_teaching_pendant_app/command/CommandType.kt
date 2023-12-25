package com.example.robot_teaching_pendant_app.command


//사용될 명령어들의 이름을 단순히 나열해 놓은 클래스입니다.
enum class CommandType {
    MOVE,

    MOVE_J,
    MOVE_JB,

    MOVE_L,
    MOVE_LB,
    MOVE_JL,

    MOVE_ITPL,


    POINT,
    CIRCLE,

    STOP,
    ROTATE
}
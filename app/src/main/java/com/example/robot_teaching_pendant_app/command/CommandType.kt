package com.example.robot_teaching_pendant_app.command


//사용될 명령어들의 이름을 단순히 나열해 놓은 클래스입니다.
enum class CommandType {
    MOVEJ,
    MOVEL,

    POINT,
    CIRCLE,

    STOP,
    ROTATE
}
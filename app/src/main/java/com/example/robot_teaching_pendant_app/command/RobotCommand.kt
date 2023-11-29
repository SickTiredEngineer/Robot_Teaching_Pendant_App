package com.example.robot_teaching_pendant_app.command


//명령어 인스턴스를 생성할 때 사용할 클래스 입니다.(명령어의 큰 틀)
//생성자의 매게변수로 Enum Class 인 CommandType 에 선언해 놓은 명령어들이 들어가게 됩니다.
data class RobotCommand(val robotCommand: CommandType){

    val type = robotCommand

    //명령어 인스턴스가 가지게 될 하위 명령어 리스트입니다.
    val childCommands = mutableListOf<RobotCommand>()


    var turn: Int? = null

}


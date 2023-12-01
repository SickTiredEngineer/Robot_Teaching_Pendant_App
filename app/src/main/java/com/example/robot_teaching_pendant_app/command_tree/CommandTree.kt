package com.example.robot_teaching_pendant_app.command_tree

import com.example.robot_teaching_pendant_app.command.RobotCommand

object CommandTree {

     //시작 후 한번만 실행될 명령어들이 들어가는 리스트로, PreProgram을 위한 명령어 리스트입니다.
     val preCommandList = mutableListOf<RobotCommand>()

     //메인 명령어 리스트입니다.
     val commandList = mutableListOf<RobotCommand>()

     //프로그램을 종료할 때 마지막에 한번만 실행될 명령어들이 들어가는 리스트로, PostProgram을 위한 명령어 리스트입니다.
     val postCommandList = mutableListOf<RobotCommand>()

}
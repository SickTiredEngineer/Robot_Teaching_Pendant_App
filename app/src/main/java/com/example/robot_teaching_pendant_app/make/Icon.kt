package com.example.robot_teaching_pendant_app.make

import CommandCircle
import CommandMoveJ
import CommandMoveL
import com.example.robot_teaching_pendant_app.R
import com.example.robot_teaching_pendant_app.command_tree.CommandTree
import com.example.robot_teaching_pendant_app.command.CommandType
import com.example.robot_teaching_pendant_app.command.RobotCommand

data class Icon(val imageRes: Int, val title: String, val action:() -> Unit)


//전체 아이콘 버튼들의 리스트 입니다.
val allIcons = listOf(
    //MoveJ 명령어를 CommandTree에 삽입하는 버튼입니다.
    Icon(R.drawable.bt_movej_icon, "MoveJ", {
        val newCommand = CommandMoveJ(CommandType.MOVE_J)
        CommandTree.commandList.add(newCommand)}),

    //MoveL 명령어를 CommandTree에 삽입하는 버튼입니다.
    Icon(R.drawable.bt_movel_icon, "MoveL", {
        val newCommand = CommandMoveL(CommandType.MOVE_L)
        CommandTree.commandList.add(newCommand)}),

    //Circle 명령어를 CommandTree에 삽입하는 버튼입니다.
    Icon(R.drawable.bt_circlemove_icon, "Circle", {
        val newCommand = CommandCircle(CommandType.CIRCLE)
        CommandTree.commandList.add(newCommand)
    }),


    Icon(R.drawable.bt_wait_icon, "Wait", { /* 동작 처리 */ }),

    // ... other icons for '전체' category
)

val moveIcons = listOf(
    Icon(R.drawable.bt_movej_icon, "동작J", { /* 동작 처리 */ }),
    Icon(R.drawable.bt_movel_icon, "동작L", { /* 동작 처리 */ }),
    // ... other icons for '이동' category
)



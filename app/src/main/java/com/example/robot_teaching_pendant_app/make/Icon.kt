package com.example.robot_teaching_pendant_app.make

import CommandCircle
import CommandMoveJ
import CommandMoveJB
import CommandMoveL
import CommandMoveLB
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

    //MoveJB 명령어를 CommandTree에 삽입하는 버튼입니다.
    Icon(R.drawable.bt_move_jb_icon, "MoveJB", {
        val newCommand = CommandMoveJB(CommandType.MOVE_JB)
        CommandTree.commandList.add(newCommand)}),


    //MoveL 명령어를 CommandTree에 삽입하는 버튼입니다.
    Icon(R.drawable.bt_movel_icon, "MoveL", {
        val newCommand = CommandMoveL(CommandType.MOVE_L)
        CommandTree.commandList.add(newCommand)}),

    //MoveLB 명령어를 CommandTree에 삽입하는 버튼입니다.
    Icon(R.drawable.bt_move_lb_icon, "MoveLB", {
        val newCommand = CommandMoveLB(CommandType.MOVE_LB)
        CommandTree.commandList.add(newCommand)}),


    //Circle 명령어를 CommandTree에 삽입하는 버튼입니다.
    Icon(R.drawable.bt_circlemove_icon, "Circle", {
        val newCommand = CommandCircle(CommandType.CIRCLE)
        CommandTree.commandList.add(newCommand)
    }),


    Icon(R.drawable.bt_wait_icon, "Wait", { /* 동작 처리 */ }),

    // ... other icons for '전체' category
)


//임시적으로 만들어 둔 카테고리, 다른 방식으로 분류를 고안중이라 사용하지 않을 가능성이 큽니다.
val moveIcons = listOf(
    Icon(R.drawable.bt_movej_icon, "동작J", { /* 동작 처리 */ }),
    Icon(R.drawable.bt_movel_icon, "동작L", { /* 동작 처리 */ }),
    // ... other icons for '이동' category
)



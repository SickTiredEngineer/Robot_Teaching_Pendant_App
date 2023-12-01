package com.example.robot_teaching_pendant_app.make

import CommandMoveJ
import CommandMoveL
import com.example.robot_teaching_pendant_app.R
import com.example.robot_teaching_pendant_app.command_tree.CommandTree
import com.example.robot_teaching_pendant_app.command.CommandType
import com.example.robot_teaching_pendant_app.command.RobotCommand

data class Icon(val imageRes: Int, val title: String, val action:() -> Unit)


//전체 아이콘 버튼들의 리스트 입니다.
val allIcons = listOf(
    Icon(R.drawable.bt_movej_icon, "MoveJ", {
        val newCommand = CommandMoveJ(CommandType.MOVEJ)
        CommandTree.commandList.add(newCommand)}),

    Icon(R.drawable.bt_movel_icon, "MoveL", {
        val newCommand = CommandMoveL(CommandType.MOVEL)
        CommandTree.commandList.add(newCommand)}),


    Icon(R.drawable.bt_circlemove_icon, "Circle", { /* 동작 처리 */ }),
    Icon(R.drawable.bt_pinpoint_icon, "Wait", { /* 동작 처리 */ }),

    // ... other icons for '전체' category
)

val moveIcons = listOf(
    Icon(R.drawable.bt_movej_icon, "동작J", { /* 동작 처리 */ }),
    Icon(R.drawable.bt_movel_icon, "동작L", { /* 동작 처리 */ }),
    // ... other icons for '이동' category
)



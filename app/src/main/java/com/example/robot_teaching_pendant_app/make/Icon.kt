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

    /**
     해당 파일은 MakeDefaultFragment에서 명령어를 Tree에 추가하기 위해 각 명령어에 맞는 아이콘을 설정 및 출력 해줍니다.
     또한 생성된 아이콘을 클릭 시, CommandTree로 RobotCommand형의 인스턴스를 생성하여 삽입합니다.
     여기서 RobotCommand형의 Classl는 Commands.kt 파일을 참고하십시오.

     MakeDefaultFragment 에 출력되는 명령어 아이콘의 형식은 MakeDefaultFragment의 displayIcons() 함수를 참고하십시오.

     추가적으로 아이콘을 나누는 카테고리를 더 추가할 예정이며, 이에 필요한 테그 또한 추가 할것입니다.
    */


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



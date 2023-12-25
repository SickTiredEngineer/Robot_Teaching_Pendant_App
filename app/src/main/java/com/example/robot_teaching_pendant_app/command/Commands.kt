import com.example.robot_teaching_pendant_app.command.CommandType
import com.example.robot_teaching_pendant_app.command.PointJ
import com.example.robot_teaching_pendant_app.command.PointL
import com.example.robot_teaching_pendant_app.command.RobotCommand

/**
명령어들을 구현하는 Class들이 모여있습니다. 명령어 Class는 RobotCommand 클래스를 상속받게 되며 이는 CommandTree의 리스트에 들어가기 위함입니다.
 명령어의 파라미터 값으로 들어갈 멤버 변수들과 해당 명령어를 실행하기 위한 실행 관련 메서드가 선언되어 있습니다.
*/


/** 아래 클래스는 명령어 MoveL입니다.
API robot function:

MoveJ (int all_motion, int index_joint, int vel, point[], int blend, uint blend_value)
1일 경우 모든 관절이 움직이고, 0일 경우 하나의 관절만 움직입니다.
- All_motion: 1- all joint moving; 0- only one joint moving
//all_motion이 1일경우, index_joint는0이고 all_motion이 1일 경우, index_joint는 어떤 관절이 움직이게 될 지 보여줍니다.
- index_joint: if all_motion = 1, then index_joint = 0, if all_motion = 0 (only 1 joint moving), index_joint shows which joint will move
//Vel은 관절의 속도를 나타내며 10% ~100% 까지입니다.
- Vel: is velocity of joint, and from 1 to 10
//point는 로봇이 움직이게 될, 혹은 거쳐가게 될 지점들 입니다.
Point: is set of points the robot will move to or through
//blend가 0일 경우, 로봇이 Point을 통과하는 동안 Blending 하지 않으며,  반대로 1일 경우 Blending 합니다.
- blend: if blend = 0, there is no blending when robot move through the points, and vice versa, when blend = 1.
//blend value는 0%~100% 까지 이며, 100%는 로봇이 움직일 두 라인 사이의 가장 짧은 라인의 1/2에 해당합니다.
- blend value: from 0% to 100%, 100% is equivalent to 1/2 of the shortest line between 2 lines that robot will move.
 */
class CommandMoveJ(robotCommand: CommandType) : RobotCommand(robotCommand){
    var allMotion: Boolean? = null
    var indexAxis: Int? = null
    var velocity: Int? = null
    var point: List<PointJ?>? =null
    var blend: Boolean? = null
    var blendValue: UInt? = null

    //명령어를 실행하는 메서드입니다.
    fun MoveJ() {
        if (allMotion == null || indexAxis == null || velocity == null || point == null || blend == null || blendValue == null) {
            throw IllegalStateException("All properties must be initialized before execution")
        }




        // 여기에 실행 로직 구현
    }
}

class CommandMoveJB(robotCommand: CommandType) : RobotCommand(robotCommand){
    var allMotion: Boolean? = null
    var indexAxis: Int? = null
    var velocity: Int? = null
    var point: List<PointJ?>? =null
    var blend: Boolean? = null
    var blendValue: UInt? = null

    //명령어를 실행하는 메서드입니다.
    fun MoveJB() {
        if (allMotion == null || indexAxis == null || velocity == null || point == null || blend == null || blendValue == null) {
            throw IllegalStateException("All properties must be initialized before execution")
        }


        // 여기에 실행 로직 구현
    }
}



/** 아래 클래스는 명령어 MoveL입니다.
API robot function:

MoveL(int all_motion, int index_axis, int vel, point[], int blend, uint blend_value)
//1일경우 모든 축이 이동하고, 0일경우 한 개의 축만 이동합니다.
- All_motion: 1- all axes moving; 0- only one axis moving
//all_motion이 1일경우, index_joint 는 0, all_motion이 0일경우 index_axis는 움직이게 될 축을 보여줍니다. ( 4개의 축이 존재 – x,y,z,roll)) -> 4개의 축이 존재??, -각 축마다 x,y,z,roll인가
- index_axis: if all_motion = 1, then index_axis = 0, if all_motion = 0 (only 1 axis moving), index_axis shows which axis will move (there are 4 axes - X, Y, Z, Roll)
//Vel은 로봇의 속도를 나타내며 10% ~ 100%까지 입니다.
- Vel: is velocity of robot, and from 10% to 100%
//point는 로봇이 움직이게 될, 혹은 거쳐가게 될 지점들 입니다.
- Point: is set of points the robot will move to or through
//blend가 0일 경우, 로봇이 Point을 통과하는 동안 Blending 하지 않으며,  반대로 1일 경우 Blending 합니다.
- blend: if blend = 0, there is no blending when robot move through the points, and vice versa, when blend = 1
//blend value는 0%~100% 까지 이며, 100%는 로봇이 움직일 두 라인 사이의 가장 짧은 라인의 1/2에 해당합니다.
- blend value: from 0% to 100%, 100% is equivalent to 1/2 of the shortest line between 2 lines that robot will move.

Ex: move following x axis by 100mm
MoveL(0, 1, 5, (100,0,0,0),0,0);
 */
class CommandMoveL(robotCommand: CommandType) : RobotCommand(robotCommand){
    var allMotion: Boolean? = null
    var indexAxis: Int? = null
    var velocity: Int? = null
    var point: List<PointL?>? =null
    var blend: Boolean? = null
    var blendValue: UInt? = null


    //명령어를 실행하는 메서드입니다.
        fun MoveL() {
        if (allMotion == null || indexAxis == null || velocity == null || point == null || blend == null || blendValue == null) {
            throw IllegalStateException("All properties must be initialized before execution")
        }

        // 여기에 실행 로직 구현
    }
}

class CommandMoveLB(robotCommand: CommandType) : RobotCommand(robotCommand){
    var allMotion: Boolean? = null
    var indexAxis: Int? = null
    var velocity: Int? = null
    var point: List<PointL?>? =null
    var blend: Boolean? = null
    var blendValue: UInt? = null


    //명령어를 실행하는 메서드입니다.
    fun MoveLB() {
        if (allMotion == null || indexAxis == null || velocity == null || point == null || blend == null || blendValue == null) {
            throw IllegalStateException("All properties must be initialized before execution")
        }

        // 여기에 실행 로직 구현
    }
}


class CommandCircle(robotCommand: CommandType) : RobotCommand(robotCommand){
    var allMotion: Boolean? = null
    var indexAxis: Int? = null
    var velocity: Int? = null
    var point: List<PointL?>? =null
    var blend: Boolean? = null
    var blendValue: UInt? = null


    //명령어를 실행하는 메서드입니다.
    fun Circle() {
        if (allMotion == null || indexAxis == null || velocity == null || point == null || blend == null || blendValue == null) {
            throw IllegalStateException("All properties must be initialized before execution")
        }

        // 여기에 실행 로직 구현
    }
}



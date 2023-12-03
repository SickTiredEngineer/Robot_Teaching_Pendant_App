package com.example.robot_teaching_pendant_app.command

/**로봇이 이동하는 지점인 Point의 인스턴스를 생성하기 위해 만들어진 Class로, 추후 파일의 위치가 변경될 수 있습니다.
 * 피드백 받은 Manual 상에는 roll(Rx,Ry,Rz)이 한 값 으로만 되어 있었기 때문에, 생성자 구조를 다음과 같이 유지 합니다.
 * 이후 필요에 따라 파라미터가 늘어날 수 있습니다.
 */

data class Point(val x: Float, val y: Float, val z: Float, val roll: Float)

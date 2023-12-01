package com.example.robot_teaching_pendant_app.command


//피드백 받은 Manual 상에는 roll(Rx,Ry,Rz)이 한 값 으로만 되어 있었기 때문에, 생성자 구조를 다음과 같이 유지 합니다.
data class Point(val x: Float, val y: Float, val z: Float, val roll: Float)

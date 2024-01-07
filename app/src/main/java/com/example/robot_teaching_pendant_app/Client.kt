package com.example.robot_teaching_pendant_app

import android.os.AsyncTask
import android.util.Log
import com.example.robot_teaching_pendant_app.system.JogState
import com.example.robot_teaching_pendant_app.system.RobotPosition
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.Socket
import java.nio.ByteBuffer
import java.nio.ByteOrder

class Client : AsyncTask<Void?, Void?, String>() {

    override fun doInBackground(vararg params: Void?): String {
        try {
            val socket = Socket("192.168.35.149", 4321)

            val mainCommandType: Byte = 0x00
            val subCommandType: Byte = 0x00

            val buffer: ByteBuffer
            if (subCommandType == 0x00.toByte()) {
                // subCommandType이 0x00인 경우
                buffer = ByteBuffer.allocate(27) // 바이트 크기 조정
                buffer.order(ByteOrder.LITTLE_ENDIAN)
                buffer.put(mainCommandType)
                buffer.put(subCommandType)
                buffer.putFloat(RobotPosition.x)
                buffer.putFloat(RobotPosition.y)
                buffer.putFloat(RobotPosition.z)
                buffer.putFloat(RobotPosition.Rx)
                buffer.putFloat(RobotPosition.Ry)
                buffer.putFloat(RobotPosition.Rz)
            } else if (subCommandType == 0x01.toByte()) {
                // subCommandType이 0x01인 경우
                buffer = ByteBuffer.allocate(39) // 바이트 크기 조정
                buffer.order(ByteOrder.LITTLE_ENDIAN)
                buffer.put(mainCommandType)
                buffer.put(subCommandType)
                buffer.putFloat(RobotPosition.x)
                buffer.putFloat(RobotPosition.y)
                buffer.putFloat(RobotPosition.z)
                buffer.putFloat(RobotPosition.Rx)
                buffer.putFloat(RobotPosition.Ry)
                buffer.putFloat(RobotPosition.Rz)
                buffer.putFloat(JogState.jogModeDist)
                buffer.putFloat(JogState.jogModeOri)
                buffer.putFloat(JogState.jogModeJoint)
            } else {
                // 다른 subCommandType의 경우를 처리할 수 있습니다.
                return "알 수 없는 subCommandType"
            }

            val sendDataBytes = buffer.array()

            // OutputStream을 사용하여 데이터 전송
            val out: OutputStream = socket.getOutputStream()
            out.write(sendDataBytes)  // 바이트 배열 직접 전송
            out.flush()

            // 서버로부터 데이터를 읽는 부분 (필요한 경우)
            val `in`: InputStream = socket.getInputStream()
            val receivedDataBytes = ByteArray(300) // 예상 받는 데이터 크기
            val readBytes: Int = `in`.read(receivedDataBytes)

            // 수신된 데이터 처리 (필요한 경우)
            if (readBytes > 0) {
                // 수신된 데이터를 사용하여 작업 수행
                // 예: ByteBuffer로 감싸서 데이터 추출
            }

            return "데이터 전송 및 수신 성공"
        } catch (e: IOException) {
            Log.e("SocketClient", "Error: " + e.message)
            return "오류 발생: " + e.message // 오류 메시지 반환
        }
    }

}

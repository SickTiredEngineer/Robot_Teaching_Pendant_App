package com.example.robot_teaching_pendant_app.connect

import ConnectionConfig
import readConfigFromFile
import java.net.Socket

object SocketManager {

    private var socket: Socket? = null
    private var configPath = "path/to/your/config.yaml"

    fun initiateConnection() {
        val config = readConfigFromFile(configPath)
        if (config != null) {
            connect(config)
        } else {
            println("YAML 파일을 읽는 중 에러가 발생했습니다.")
        }
    }

    private fun connect(config: ConnectionConfig) {
        // 별도의 스레드에서 소켓 연결을 수행합니다.
        Thread {
            try {
                socket = Socket(config.ip, config.port)
                // 소켓 연결 후 데이터 수신 등 처리
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }

    fun sendData(data: String) {
        // 데이터 전송 로직
    }

    fun close() {
        try {
            socket?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

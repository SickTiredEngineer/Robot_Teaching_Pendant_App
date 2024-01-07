package com.example.robot_teaching_pendant_app.connect

import ConnectionConfig
import readConfigFromFile
import java.net.Socket

object SocketManager {

    /**
    싱글턴 패턴을 사용한 연결 소켓을 관리하는 파일입니다.
    프로그램 계획에 따라 총 2개의 소켓을 열어서 사용할 예정입니다.
    -> 명령을 보내는 소켓, 0.5초마다 로봇의 값을 받아오는 소켓 등...
    다른 좋은 연결 설계가 있다면 변경될 수 있습니다.
     */

    private var socket: Socket? = null

    //연결에 필요한 IP, PORT1, PORT2
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

import org.yaml.snakeyaml.Yaml
import java.io.File
import java.io.InputStream
import java.net.Socket

    /**
    config.yaml 파일을 읽어와서 IP와 Port1~2 번호를 추출하는 데이터 클래스 입니다.
     Socket Manager와 함께, 프로그램 설계중 변경될 수 있습니다.
    */

data class ConnectionConfig(val ip: String, val port: Int)

fun readConfigFromFile(filePath: String): ConnectionConfig? {
    try {
        val inputStream: InputStream = File(filePath).inputStream()
        val yaml = Yaml()
        val data = yaml.load<Map<String, Any>>(inputStream)

        val ip = data["ip"] as? String ?: return null
        val port = (data["port"] as? Int) ?: return null


        //두 번째 포트를 읽어오는 부분입니다. 현재 사용하지는 않아서 주석 처리 하였습니다.
//        val secondPort = (data["secondPort"] as? Int) ?: return null

        return ConnectionConfig(ip, port)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}

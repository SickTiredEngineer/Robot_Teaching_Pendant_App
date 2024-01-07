import org.yaml.snakeyaml.Yaml
import java.io.File
import java.io.InputStream
import java.net.Socket

data class ConnectionConfig(val ip: String, val port: Int)

fun readConfigFromFile(filePath: String): ConnectionConfig? {
    try {
        val inputStream: InputStream = File(filePath).inputStream()
        val yaml = Yaml()
        val data = yaml.load<Map<String, Any>>(inputStream)

        val ip = data["ip"] as? String ?: return null
        val port = (data["port"] as? Int) ?: return null

        return ConnectionConfig(ip, port)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}

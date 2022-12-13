package pairmatching.model.resources

import java.io.IOException
import java.nio.file.Paths
import kotlin.io.path.readLines

object ResourceManager {

    private const val BASE_URI = "src/main/kotlin/resources/"

    fun readCrewNames(fileName: String): List<String>? {
        return try {
            Paths.get(BASE_URI + fileName).readLines()
        } catch (_: IOException) {
            null
        }
    }

}
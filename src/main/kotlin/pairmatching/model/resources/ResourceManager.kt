package pairmatching.model.resources

import pairmatching.model.data.mission.Course
import pairmatching.model.data.mission.Level
import pairmatching.model.data.mission.Mission
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

    fun getAllMissions(): List<Mission> {
        return loopCourses()
    }

    private fun loopCourses(): List<Mission> {
        val result = mutableListOf<Mission>()

        for (course in Course.values()) {
            result.addAll(loopLevels(course))
        }

        return result
    }

    private fun loopLevels(course: Course): List<Mission> {
        val result = mutableListOf<Mission>()

        for (level in Level.values()) {
            result.addAll(loopMissionNames(course, level))
        }

        return result
    }

    private fun loopMissionNames(course: Course, level: Level): List<Mission> {
        val result = mutableListOf<Mission>()

        for (missionName in Resource.MissionNames[level]!!) {
            result.add(Mission(course, level, missionName))
        }

        return result
    }
}
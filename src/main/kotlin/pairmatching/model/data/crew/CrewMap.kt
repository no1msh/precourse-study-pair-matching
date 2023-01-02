package pairmatching.model.data.crew

import pairmatching.model.data.mission.Course
import java.util.EnumMap

class CrewMap(vararg pairs: Pair<Course, List<Crew>>) {

    private val map: EnumMap<Course, List<Crew>> = EnumMap(mapOf(*pairs))

    operator fun get(course: Course): List<Crew> = map[course]!!
}
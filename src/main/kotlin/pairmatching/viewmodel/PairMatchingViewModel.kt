package pairmatching.viewmodel

import pairmatching.model.data.crew.CrewPairList
import pairmatching.model.data.mission.Course
import pairmatching.model.data.mission.Level
import pairmatching.model.data.mission.Mission
import pairmatching.model.repository.PairMatchingRepository
import pairmatching.view.io.data.MissionDashboard
import pairmatching.model.data.result.Result
import java.util.TreeMap

class PairMatchingViewModel(
    private val repository: PairMatchingRepository
) {

    private var _missionDashboard: MissionDashboard? = null
    val missionDashboard: MissionDashboard get() = requireNotNull(_missionDashboard)

    val sampleMission: Mission
        get() = missionDashboard.missions.values.first { it.isNotEmpty() }.first()

    fun loadCrews(): Boolean {
        return repository.loadCrews()
    }

    fun loadMissions(): Boolean {
        val missions = repository.loadMissions()

        _missionDashboard = makeMissionDashboard(missions)

        return true
    }

    private fun makeMissionDashboard(missions: List<Mission>): MissionDashboard {
        return MissionDashboard(
            courses = makeCourses(missions),
            missions = makeMissionMap(missions)
        )
    }

    private fun makeCourses(missions: List<Mission>): List<Course> {
        return missions.map { it.course }.distinct()
    }

    private fun makeMissionMap(missions: List<Mission>): Map<Level, List<Mission>> {
        val missionMap = TreeMap<Level, MutableList<Mission>>()

        for (level in Level.values()) {
            missionMap[level] = mutableListOf()
        }

        for (mission in missions) {
            missionMap[mission.level]!!.add(mission)
        }

        return missionMap.mapValues { entry ->
            entry.value.distinctBy { it.name }
        }
    }

    fun isExistsMission(mission: Mission): Boolean {
        return repository.isExistsMission(mission)
    }

    fun isNotExistsMission(mission: Mission): Boolean {
        return !repository.isExistsMission(mission)
    }

    fun isExistsPairMatchingHistory(mission: Mission): Boolean {
        return repository.isExistsPairMatchingHistory(mission)
    }

    fun isNotExistsPairMatchingHistory(mission: Mission): Boolean {
        return !repository.isExistsPairMatchingHistory(mission)
    }

    fun runPairMatching(mission: Mission): Result<CrewPairList> {
        return repository.runPairMatching(mission)
    }

    fun getPairMatchingHistory(mission: Mission): CrewPairList {
        return repository.getPairMatchingHistory(mission)
    }

    fun clearPairMatchingHistories() {
        repository.clearPairMatchingHistories()
    }
}
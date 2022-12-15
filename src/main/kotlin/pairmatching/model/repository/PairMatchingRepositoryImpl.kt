package pairmatching.model.repository

import pairmatching.model.data.crew.Crew
import pairmatching.model.data.crew.CrewPairMatchingMap
import pairmatching.model.data.mission.Course
import pairmatching.model.data.mission.Mission
import pairmatching.model.resources.Resource
import pairmatching.model.resources.ResourceManager
import pairmatching.model.data.result.Result
import pairmatching.model.data.result.Result.*

class PairMatchingRepositoryImpl : PairMatchingRepository {

    private lateinit var backendCrews: List<Crew>
    private lateinit var frontendCrews: List<Crew>
    private lateinit var pairMatchingHistory: CrewPairMatchingMap

    override fun loadCrews(): Boolean {
        backendCrews = readCrewNames(Course.BACKEND) ?: return false
        frontendCrews = readCrewNames(Course.FRONTEND) ?: return false

        return true
    }

    private fun readCrewNames(course: Course): List<Crew>? = when (course) {
        Course.BACKEND -> ResourceManager.readCrewNames(Resource.BACKEND_CREW_FILE_NAME)
        Course.FRONTEND -> ResourceManager.readCrewNames(Resource.FRONTEND_CREW_FILE_NAME)
    }?.map { Crew(course, it) }

    override fun loadMissions(): Boolean {
        pairMatchingHistory = CrewPairMatchingMap(
            ResourceManager.getAllMissions()
        )

        return true
    }

    override fun isExistsMission(mission: Mission): Result<Boolean> {
        val history = pairMatchingHistory[mission]

        return if (history != null) {
            Success(history.isNotEmpty())
        } else {
            Failure()
        }
    }
}
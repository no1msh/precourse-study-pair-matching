package pairmatching.model.repository

import pairmatching.model.data.crew.Crew
import pairmatching.model.data.crew.CrewPair
import pairmatching.model.data.crew.CrewPairList
import pairmatching.model.data.crew.CrewPairMatchingMap
import pairmatching.model.data.mission.Course
import pairmatching.model.data.mission.Level
import pairmatching.model.data.mission.Mission
import pairmatching.model.resources.Resource
import pairmatching.model.resources.ResourceManager
import pairmatching.model.shuffler.CrewShuffler
import pairmatching.model.shuffler.RandomCrewShuffler
import pairmatching.model.data.result.Result
import pairmatching.model.data.result.Result.Success
import pairmatching.model.data.result.Result.Failure

class PairMatchingRepositoryImpl : PairMatchingRepository {

    private val shuffler: CrewShuffler = CrewShuffler(RandomCrewShuffler())

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

    override fun isExistsMission(mission: Mission): Boolean {
        return pairMatchingHistory.containsKey(mission)
    }

    override fun isExistsPairMatchingHistory(mission: Mission): Boolean {
        check(isExistsMission(mission)) { "Not exists mission" }

        return pairMatchingHistory[mission]!!.isNotEmpty()
    }

    override fun matchCrewPair(mission: Mission): Result<CrewPairList> {
        check(isExistsMission(mission)) { "Not exists mission" }

        val crews = when (mission.course) {
            Course.BACKEND -> backendCrews
            Course.FRONTEND -> frontendCrews
        }

        val shuffledCrewPairs = shuffled(mission, crews, MATCHING_TRY_COUNT)

        return if (shuffledCrewPairs != null) {
            pairMatchingHistory[mission] = shuffledCrewPairs
            Success(shuffledCrewPairs)
        } else {
            Failure()
        }
    }

    private tailrec fun shuffled(
        mission: Mission,
        crews: List<Crew>,
        count: Int
    ): CrewPairList? {
        if (count == 0) {
            return null
        }
        val shuffled = shuffler.shuffled(crews)
        val crewPairs = makeCrewPairList(shuffled)

        return if (checkOtherMission(mission, crewPairs)) {
            crewPairs
        } else {
            shuffled(mission, crews, count - 1)
        }
    }

    private fun makeCrewPairList(crews: List<Crew>): CrewPairList {
        val crewPairs = if (crews.size % 2 == 0) {
            crews.chunked(2, ::CrewPair)
        } else {
            crews.dropLast(3)
                .chunked(2, ::CrewPair)
                .plusElement(CrewPair(crews.takeLast(3)))
        }

        return CrewPairList(crewPairs)
    }

    private fun checkOtherMission(mission: Mission, crewPairs: CrewPairList): Boolean {
        for (level in Level.dropOf(mission.level)) {
            val otherMission = mission.copy(level = level)
            val otherCrewPairs = pairMatchingHistory[otherMission]

            if (otherCrewPairs.anyDuplicated(crewPairs)) {
                return false
            }
        }
        return true
    }

    override fun getCrewPairs(mission: Mission): CrewPairList {
        check(isExistsMission(mission)) { "Not exists mission" }
        check(isExistsPairMatchingHistory(mission)) { "Not exists pair matching history" }

        return pairMatchingHistory[mission]
    }

    override fun clearAllPairMatchingHistory() {
        pairMatchingHistory.clearAllValues()
    }

    companion object {
        private const val MATCHING_TRY_COUNT = 3
    }
}
package pairmatching.model.repository

import pairmatching.model.data.crew.*
import pairmatching.model.data.mission.Course
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

    private lateinit var crews: CrewMap
    private lateinit var pairMatchingHistory: CrewPairMatchingMap

    override fun loadCrews(): Boolean {
        val backendCrews = readCrewNames(Course.BACKEND) ?: return false
        val frontendCrews = readCrewNames(Course.FRONTEND) ?: return false

        crews = CrewMap(
            Course.BACKEND to backendCrews,
            Course.FRONTEND to frontendCrews,
        )

        return true
    }

    private fun readCrewNames(course: Course): List<Crew>? = when (course) {
        Course.BACKEND -> ResourceManager.readCrewNames(Resource.BACKEND_CREW_FILE_NAME)
        Course.FRONTEND -> ResourceManager.readCrewNames(Resource.FRONTEND_CREW_FILE_NAME)
    }?.map { Crew(course, it) }

    override fun loadMissions(): List<Mission> {
        val missions = ResourceManager.getAllMissions()

        pairMatchingHistory = CrewPairMatchingMap(missions)

        return missions
    }

    override fun isExistsMission(mission: Mission): Boolean {
        return pairMatchingHistory.containsKey(mission)
    }

    override fun isExistsPairMatchingHistory(mission: Mission): Boolean {
        check(isExistsMission(mission)) { "Not exists mission" }

        return pairMatchingHistory[mission].isNotEmpty()
    }

    override fun runPairMatching(mission: Mission): Result<CrewPairList> {
        check(isExistsMission(mission)) { "Not exists mission" }

        val crews = crews[mission.course]
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

        return if (checkDuplicatedOtherMissions(mission, crewPairs)) {
            crewPairs
        } else {
            shuffled(mission, crews, count - 1)
        }
    }

    private fun makeCrewPairList(crews: List<Crew>): CrewPairList {
        val crewPairs = if (crews.size % 2 == 0) {
            crews.asSequence()
                .chunked(2)
                .map(::CrewPair)
                .toList()
        } else {
            crews.dropLast(3)
                .asSequence()
                .chunked(2)
                .map(::CrewPair)
                .toList()
                .plusElement(CrewPair(crews.takeLast(3)))
        }

        return CrewPairList(crewPairs)
    }

    private fun checkDuplicatedOtherMissions(mission: Mission, crewPairs: CrewPairList): Boolean {
        return pairMatchingHistory
            .filter { it.key.level == mission.level }
            .filterNot { it.key == mission }
            .none { it.value.anyDuplicated(crewPairs) }
    }

    override fun getPairMatchingHistory(mission: Mission): CrewPairList {
        check(isExistsMission(mission)) { "Not exists mission" }
        check(isExistsPairMatchingHistory(mission)) { "Not exists pair matching history" }

        return pairMatchingHistory[mission]
    }

    override fun clearPairMatchingHistories() {
        pairMatchingHistory.clearAllValues()
    }

    companion object {
        private const val MATCHING_TRY_COUNT = 3
    }
}
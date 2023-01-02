package pairmatching.model.repository

import pairmatching.model.data.crew.CrewPairList
import pairmatching.model.data.mission.Mission
import pairmatching.model.data.result.Result

interface PairMatchingRepository {

    fun loadCrews(): Boolean

    fun loadMissions(): List<Mission>

    fun isExistsMission(mission: Mission): Boolean

    fun isExistsPairMatchingHistory(mission: Mission): Boolean

    fun runPairMatching(mission: Mission): Result<CrewPairList>

    fun getPairMatchingHistory(mission: Mission): CrewPairList

    fun clearPairMatchingHistories()
}
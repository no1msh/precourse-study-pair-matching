package pairmatching.model.repository

import pairmatching.model.data.crew.CrewPairList
import pairmatching.model.data.mission.Mission
import pairmatching.model.data.result.Result

interface PairMatchingRepository {

    fun loadCrews(): Boolean

    fun loadMissions(): Boolean

    fun isExistsMission(mission: Mission): Boolean

    fun isExistsPairMatchingHistory(mission: Mission): Boolean

    fun matchCrewPair(mission: Mission): Result<CrewPairList>

    fun getCrewPairs(mission: Mission): CrewPairList
}
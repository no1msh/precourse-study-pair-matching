package pairmatching.model.repository

import pairmatching.model.data.mission.Mission
import pairmatching.model.data.result.Result

interface PairMatchingRepository {

    fun loadCrews(): Boolean

    fun loadMissions(): Boolean

    fun isExistsMission(mission: Mission): Result<Boolean>

}
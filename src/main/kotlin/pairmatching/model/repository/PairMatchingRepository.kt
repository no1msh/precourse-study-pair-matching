package pairmatching.model.repository

import pairmatching.model.data.mission.Mission

interface PairMatchingRepository {

    fun loadCrews(): Boolean

    fun loadMissions(): Boolean

    fun isExistsMission(mission: Mission): Boolean

    fun isExistsPairMatchingHistory(mission: Mission): Boolean

}
package pairmatching.model.repository

interface PairMatchingRepository {

    fun loadCrews(): Boolean

    fun loadMissions(): Boolean
}
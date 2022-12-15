package pairmatching.model.data.crew

import pairmatching.model.data.mission.Mission

class CrewPairMatchingMap(missions: List<Mission>) {

    private val elements: Map<Mission, CrewPairList> =
        missions.associateWith { CrewPairList(emptyList()) }

    operator fun get(mission: Mission): CrewPairList {
        return elements[mission]!!
    }

    fun containsKey(mission: Mission): Boolean {
        return elements.containsKey(mission)
    }
}
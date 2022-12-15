package pairmatching.model.data.crew

import pairmatching.model.data.mission.Mission

class CrewPairMatchingMap(missions: List<Mission>) {

    private val elements: MutableMap<Mission, CrewPairList> =
        missions.associateWith { CrewPairList(emptyList()) }.toMutableMap()

    operator fun get(mission: Mission): CrewPairList {
        return elements[mission]!!
    }

    operator fun set(mission: Mission, crewPairs: CrewPairList) {
        elements[mission] = crewPairs
    }

    fun containsKey(mission: Mission): Boolean {
        return elements.containsKey(mission)
    }
}
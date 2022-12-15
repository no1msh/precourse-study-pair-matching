package pairmatching.model.data.crew

import pairmatching.model.data.mission.Mission

class CrewPairMatchingMap(missions: List<Mission>) : Iterable<Map.Entry<Mission, CrewPairList>> {

    private val elements: MutableMap<Mission, CrewPairList> =
        missions.associateWith { CrewPairList.empty() }.toMutableMap()

    operator fun get(mission: Mission): CrewPairList {
        return elements[mission]!!
    }

    operator fun set(mission: Mission, crewPairs: CrewPairList) {
        elements[mission] = crewPairs
    }

    override fun iterator(): Iterator<Map.Entry<Mission, CrewPairList>> {
        return (elements as Map<Mission, CrewPairList>).iterator()
    }

    fun containsKey(mission: Mission): Boolean {
        return elements.containsKey(mission)
    }

    fun clearAllValues() {
        elements.entries.forEach {
            it.setValue(CrewPairList.empty())
        }
    }
}
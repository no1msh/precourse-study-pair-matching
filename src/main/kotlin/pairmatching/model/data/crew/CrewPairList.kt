package pairmatching.model.data.crew

class CrewPairList(
    private val crewPairs: List<CrewPair>
) : Iterable<CrewPair> {

    override fun iterator(): Iterator<CrewPair> = crewPairs.iterator()

    fun isNotEmpty(): Boolean = crewPairs.isNotEmpty()

    fun anyDuplicated(other: CrewPairList): Boolean {
        for (pair in crewPairs) {
            if (other.crewPairs.any { it == pair }) return true
        }
        return false
    }
}
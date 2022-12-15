package pairmatching.model.data.crew

class CrewPairList(
    private val crewPairs: List<CrewPair>
) : Iterable<CrewPair> {

    override fun iterator(): Iterator<CrewPair> = crewPairs.iterator()
}
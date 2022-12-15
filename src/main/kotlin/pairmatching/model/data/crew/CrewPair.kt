package pairmatching.model.data.crew

class CrewPair : Iterable<Crew> {

    private val crews: List<Crew>

    constructor(first: Crew, second: Crew) {
        crews = listOf(first, second)
    }
    constructor(first: Crew, second: Crew, third: Crew) {
        crews = listOf(first, second, third)
    }

    override fun iterator(): Iterator<Crew> = crews.iterator()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        return other is CrewPair
                && crews.size == other.crews.size
                && crews.containsAll(other.crews)
    }

    override fun hashCode(): Int {
        return crews.hashCode()
    }
}
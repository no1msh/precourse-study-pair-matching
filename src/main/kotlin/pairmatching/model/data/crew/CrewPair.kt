package pairmatching.model.data.crew

class CrewPair(private val crews: List<Crew>) : Iterable<Crew> {

    val size: Int
        get() = crews.size

    init {
        require(crews.size in MIN_SIZE..MAX_SIZE)
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

    companion object {
        const val MIN_SIZE = 2
        const val MAX_SIZE = 3
    }
}
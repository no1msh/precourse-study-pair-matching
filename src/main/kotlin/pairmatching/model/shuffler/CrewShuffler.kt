package pairmatching.model.shuffler

import pairmatching.model.data.crew.Crew

class CrewShuffler(private val shuffler: ICrewShuffler) {

    fun shuffled(crews: List<Crew>): List<Crew> {
        return shuffler.shuffled(crews)
    }

}
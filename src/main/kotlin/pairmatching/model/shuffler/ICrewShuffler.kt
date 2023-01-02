package pairmatching.model.shuffler

import pairmatching.model.data.crew.Crew

interface ICrewShuffler {
    fun shuffled(crews: List<Crew>): List<Crew>
}
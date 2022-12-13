package pairmatching.model.shuffler

import pairmatching.model.data.Crew

interface ICrewShuffler {
    fun shuffled(crews: List<Crew>): List<Crew>
}
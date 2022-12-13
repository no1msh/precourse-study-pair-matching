package pairmatching.model.shuffler

import camp.nextstep.edu.missionutils.Randoms
import pairmatching.model.data.Crew

class RandomCrewShuffler : ICrewShuffler {

    override fun shuffled(crews: List<Crew>): List<Crew> {
        return Randoms.shuffle(crews)
    }
}
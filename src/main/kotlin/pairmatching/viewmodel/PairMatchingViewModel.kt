package pairmatching.viewmodel

import pairmatching.model.repository.PairMatchingRepository

class PairMatchingViewModel(
    private val repository: PairMatchingRepository
) {

    fun loadCrews() {
        repository.loadCrews()
    }

    fun loadMissions() {
        repository.loadMissions()
    }
}
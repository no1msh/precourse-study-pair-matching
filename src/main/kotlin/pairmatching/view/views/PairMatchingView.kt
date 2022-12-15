package pairmatching.view.views

import pairmatching.model.repository.PairMatchingRepositoryImpl
import pairmatching.view.io.InputView
import pairmatching.view.io.OutputView
import pairmatching.viewmodel.PairMatchingViewModel

class PairMatchingView(
    private val inputView: InputView,
    private val outputView: OutputView,
) : ConsoleView {

    private lateinit var viewModel: PairMatchingViewModel

    override fun onInit() {
        viewModel = PairMatchingViewModel(
            PairMatchingRepositoryImpl()
        )

        with (viewModel) {
            loadCrews()
            loadMissions()
        }
    }

    override fun onResume() {
        TODO("Not yet implemented")
    }

    override fun onFinish(): Boolean {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        TODO("Not yet implemented")
    }
}
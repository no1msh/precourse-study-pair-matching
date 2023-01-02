package pairmatching.view.views

import pairmatching.model.data.mission.Mission
import pairmatching.model.data.result.Result.*
import pairmatching.model.repository.PairMatchingRepositoryImpl
import pairmatching.view.io.InputView
import pairmatching.view.io.OutputView
import pairmatching.view.io.data.TaskCommand
import pairmatching.viewmodel.PairMatchingViewModel

class PairMatchingView(
    private val inputView: InputView,
    private val outputView: OutputView,
) : ConsoleView() {

    private lateinit var viewModel: PairMatchingViewModel

    override fun onCreate() {
        viewModel = PairMatchingViewModel(
            PairMatchingRepositoryImpl()
        )

        with (viewModel) {
            loadCrews()
            loadMissions()
        }
    }

    override fun onResume() {
        outputView.printTaskList()

        when (repeatIfThrows(inputView::readTaskCommand)) {
            TaskCommand.PairMatching -> runPairMatching()
            TaskCommand.PairInquiry -> runInquiryPair()
            TaskCommand.PairInitialize -> clearPairMatchingHistories()
            TaskCommand.Quit -> finish()
        }
    }

    private fun runPairMatching() {
        outputView.printMissionDashboard(viewModel.missionDashboard)

        val mission = readMissionForPairMatching()

        when (val result = viewModel.runPairMatching(mission)) {
            is Success -> outputView.printPairMatchingResult(result.data)
            is Failure -> outputView.printPairMatchingError()
        }
    }

    private tailrec fun readMissionForPairMatching(): Mission {
        outputView.printSelectMission(viewModel.sampleMission)

        val mission = repeatIfThrows {
            inputView.readMission()
        }

        if (viewModel.isNotExistsMission(mission)) {
            outputView.printNotExistsMissionError()
            return readMissionForPairMatching()
        }
        if (viewModel.isExistsPairMatchingHistory(mission)) {
            outputView.printExistsAlreadyPairMatchingHistory()

            val biCommand = repeatIfThrows {
                inputView.readBiCommand()
            }
            if (biCommand.isNo) {
                return readMissionForPairMatching()
            }
        }

        return mission
    }

    private fun runInquiryPair() {
        outputView.printMissionDashboard(viewModel.missionDashboard)

        val mission = readMissionForInquiryPair()
        val result = viewModel.getPairMatchingHistory(mission)

        outputView.printPairMatchingResult(result)
    }

    private tailrec fun readMissionForInquiryPair(): Mission {
        outputView.printSelectMission(viewModel.sampleMission)

        val mission = repeatIfThrows {
            inputView.readMission()
        }
        if (viewModel.isNotExistsMission(mission)) {
            outputView.printNotExistsMissionError()
            return readMissionForInquiryPair()
        }
        if (viewModel.isNotExistsPairMatchingHistory(mission)) {
            outputView.printNotExistsPairMatchingHistoryError()
            return readMissionForInquiryPair()
        }

        return mission
    }

    private fun clearPairMatchingHistories() {
        outputView.printInitializedPairMatchingHistories()

        viewModel.clearPairMatchingHistories()
    }

    override fun onDestroy() {
        viewModel.clearPairMatchingHistories()
    }

    private inline fun <R> repeatIfThrows(block: () -> R): R {
        while (true) {
            try {
                return block()
            } catch (t: Throwable) {
                outputView.printError(t)
            }
        }
    }
}
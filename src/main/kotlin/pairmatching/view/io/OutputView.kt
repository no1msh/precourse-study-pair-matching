package pairmatching.view.io

import pairmatching.model.data.crew.CrewPairList
import pairmatching.model.data.mission.Course
import pairmatching.model.data.mission.Level
import pairmatching.model.data.mission.Mission
import pairmatching.view.io.data.MissionDashboard
import pairmatching.view.io.data.TaskCommand
import pairmatching.view.strings.ErrorMessage
import pairmatching.view.strings.Message

class OutputView {

    fun printTaskList() {
        println(Message.SELECT_TASK)
        println(TaskCommand.values().joinToString(Message.LineSeparator))
    }

    fun printMissionDashboard(dashboard: MissionDashboard) {
        val message = Message.MissionDashboard.Format.format(
            buildStringOf(dashboard.courses),
            buildStringOf(dashboard.missions),
        )

        println(message)
    }

    private fun buildStringOf(courses: List<Course>): String {
        return courses.joinToString(SEPARATOR_BAR)
    }

    private fun buildStringOf(missions: Map<Level, List<Mission>>): String {
        val formatter = Message.MissionDashboard.EachMissionFormat

        return Level.values().joinToString(Message.LineSeparator) { level ->
            formatter.format(
                level,
                missions[level]
                    ?.joinToString(SEPARATOR_BAR) { it.name }
                    ?.ifBlank { "" }
            )
        }
    }

    fun printSelectMission(sample: Mission) {
        println(Message.SelectMission.Format.format(
            sample.course, sample.level, sample.name
        ))
    }

    fun printPairMatchingResult(crewPairs: CrewPairList) {
        println(Message.PAIR_MATCHING_RESULTS)
        println(buildString {
            for (crewPair in crewPairs) {
                val pairString = crewPair.joinToString(SEPARATOR_COLON) {
                    it.name
                }

                appendLine(pairString)
            }
        })
    }

    fun printExistsAlreadyPairMatchingHistory() {
        println(Message.RETRY_REMATCHING_GUIDE)
    }

    fun printInitializedPairMatchingHistories() {
        println2(Message.INITIALIZED)
    }

    fun printError(t: Throwable) {
        printError(t.message!!)
    }

    fun printNotExistsMissionError() {
        printError(ErrorMessage.NOT_EXISTS)
    }

    fun printNotExistsPairMatchingHistoryError() {
        printError(Message.NOT_EXISTS_PAIR_MATCHING_HISTORY)
    }

    fun printPairMatchingError() {
        printError(ErrorMessage.PAIR_MATCHING_FAILED)
    }

    private fun printError(message: String) {
        println2("\n${ErrorMessage.PREFIX} $message")
    }

    private fun println2(message: String) {
        println(message + LineSeparator)
    }

    companion object {
        const val SEPARATOR_BAR = Message.MissionDashboard.SEPARATOR
        const val SEPARATOR_COMMA = Message.MissionDashboard.COMMA
        const val SEPARATOR_COLON = Message.PAIR_SEPARATOR

        val LineSeparator: String = Message.LineSeparator
    }
}
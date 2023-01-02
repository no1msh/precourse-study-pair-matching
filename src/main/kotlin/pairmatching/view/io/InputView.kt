package pairmatching.view.io

import camp.nextstep.edu.missionutils.Console
import pairmatching.model.data.mission.Course
import pairmatching.model.data.mission.Level
import pairmatching.model.data.mission.Mission
import pairmatching.util.valueEnumOf
import pairmatching.view.io.data.BiCommand
import pairmatching.view.io.data.TaskCommand
import pairmatching.view.io.validator.InputValidator

class InputView {

    fun readTaskCommand(): TaskCommand {
        val input = Console.readLine()

        InputValidator.validateTaskCommand(input)

        return valueEnumOf(input)
    }

    fun readBiCommand(): BiCommand {
        val input = Console.readLine()

        InputValidator.validateBiCommand(input)

        return valueEnumOf(input)
    }

    fun readMission(): Mission {
        val inputs = Console.readLine()
            .split(SEPARATOR_COMMA)
            .map { it.trim() }

        InputValidator.validateMission(inputs)

        return Mission(inputs[0], inputs[1], inputs[2])
    }

    private fun Mission(course: String, level: String, name: String): Mission = Mission(
        course = valueEnumOf<Course>(course),
        level = valueEnumOf<Level>(level),
        name = name,
    )



    companion object {
        const val SEPARATOR_COMMA = ","
    }
}
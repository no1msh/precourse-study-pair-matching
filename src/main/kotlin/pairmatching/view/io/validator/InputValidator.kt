package pairmatching.view.io.validator

import pairmatching.model.data.mission.Course
import pairmatching.model.data.mission.Level
import pairmatching.util.valueEnumContains
import pairmatching.view.io.data.BiCommand
import pairmatching.view.io.data.TaskCommand
import pairmatching.view.strings.ErrorMessage

object InputValidator {

    const val INPUT_MISSION_INFO_SIZE = 3

    fun validateTaskCommand(input: String) {
        require(valueEnumContains<TaskCommand>(input)) { ErrorMessage.INVALID_COMMAND }
    }

    fun validateBiCommand(input: String) {
        require(valueEnumContains<BiCommand>(input)) { ErrorMessage.INVALID_COMMAND }
    }

    fun validateMission(inputs: List<String>) {
        require(inputs.size == INPUT_MISSION_INFO_SIZE) { ErrorMessage.INVALID_COMMAND }
        require(valueEnumContains<Course>(inputs[0])) { ErrorMessage.INVALID_COMMAND }
        require(valueEnumContains<Level>(inputs[1])) { ErrorMessage.INVALID_COMMAND }
    }
}
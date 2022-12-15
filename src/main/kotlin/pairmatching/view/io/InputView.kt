package pairmatching.view.io

import camp.nextstep.edu.missionutils.Console
import pairmatching.view.strings.ErrorMessage

class InputView {

    fun readTaskCommand(): TaskCommand {
        return requireNotNull(
            TaskCommand.valueOf(Console.readLine())
        ) {
            ErrorMessage.INVALID_COMMAND
        }
    }
}
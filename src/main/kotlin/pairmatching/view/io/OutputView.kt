package pairmatching.view.io

import pairmatching.view.strings.ErrorMessage
import pairmatching.view.strings.Message

class OutputView {

    fun printTaskList() {
        println(Message.SELECT_TASK)
        println(TaskCommand.values().joinToString(Message.LineSeparator))
    }

    fun printError(t: Throwable) {
        println(ErrorMessage.PREFIX + t.message)
    }
}
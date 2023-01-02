package pairmatching

import pairmatching.app.PairMatchingApplication
import pairmatching.view.io.InputView
import pairmatching.view.io.OutputView
import pairmatching.view.views.PairMatchingView

fun main() {
    val view = PairMatchingView(InputView(), OutputView())
    val application = PairMatchingApplication(view)
    application.run()
}
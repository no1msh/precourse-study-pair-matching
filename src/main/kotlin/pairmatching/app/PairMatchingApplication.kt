package pairmatching.app

import pairmatching.view.views.ConsoleView

class PairMatchingApplication(private val view: ConsoleView) {

    fun run() {
        view.onCreate()

        do {
            view.onResume()
        } while (!view.isFinished)

        view.onDestroy()
    }
}
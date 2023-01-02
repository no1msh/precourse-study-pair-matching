package pairmatching.view.views

abstract class ConsoleView {

    var isFinished: Boolean = false
        private set

    abstract fun onCreate()

    abstract fun onResume()

    abstract fun onDestroy()

    fun finish() {
        isFinished = true
    }
}
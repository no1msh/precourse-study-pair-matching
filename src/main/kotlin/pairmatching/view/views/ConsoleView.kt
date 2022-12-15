package pairmatching.view.views

interface ConsoleView {

    fun onInit()

    fun onResume()

    fun onFinish(): Boolean

    fun onDestroy()
}
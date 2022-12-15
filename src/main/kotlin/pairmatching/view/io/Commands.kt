package pairmatching.view.io

enum class TaskCommand(val command: String, val description: String) {
    PairMatching("1", "페어 매칭"),
    PairInquiry("2", "페어 조회"),
    PairInitialize("3", "페어 초기화"),
    Quit("Q", "종료"),;

    override fun toString(): String = "$command. $description"
}

enum class BiCommand(val command: String) {
    Yes("네"),
    No("아니오"),;

    val isYes: Boolean get() = this == Yes
    val isNo: Boolean get() = this == No

    override fun toString(): String = command
}
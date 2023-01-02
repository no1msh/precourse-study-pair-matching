package pairmatching.view.io.data

import pairmatching.util.ValueEnum

enum class TaskCommand(override val value: String, val description: String) : ValueEnum<TaskCommand> {

    PairMatching("1", "페어 매칭"),
    PairInquiry("2", "페어 조회"),
    PairInitialize("3", "페어 초기화"),
    Quit("Q", "종료"),;

    override fun toString(): String = "$value. $description"
}

enum class BiCommand(override val value: String) : ValueEnum<BiCommand> {

    Yes("네"),
    No("아니오"),;

    val isYes: Boolean get() = this == Yes
    val isNo: Boolean get() = this == No

    override fun toString(): String = value
}

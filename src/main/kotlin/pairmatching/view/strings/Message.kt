package pairmatching.view.strings

object Message {

    const val SELECT_TASK = "기능을 선택하세요."
    const val PAIR_MATCHING_RESULTS = "페어 매칭 결과입니다."
    const val PAIR_SEPARATOR = " : "
    const val RETRY_REMATCHING_GUIDE = "매칭 정보가 있습니다. 다시 매칭하시겠습니까?"
    const val NOT_EXISTS_PAIR_MATCHING_HISTORY = "매칭 정보가 존재하지 않습니다."
    const val INITIALIZED = "초기화 되었습니다."

    val LineSeparator: String = System.lineSeparator()

    object MissionDashboard {

        const val SEPARATOR = " | "
        const val COMMA = ", "
        const val EachMissionFormat = "  - %s: %s"

        val Format: String = """
            #############################################
            과정: %s
            미션:
            %s
            #############################################
        """.trimIndent()
    }

    object SelectMission {

        val Format: String = """
            과정, 레벨, 미션을 선택하세요.
            ex) %s, %s, %s
        """.trimIndent()
    }
}
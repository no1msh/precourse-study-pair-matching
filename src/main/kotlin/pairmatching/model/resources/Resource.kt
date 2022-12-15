package pairmatching.model.resources

import pairmatching.model.data.mission.Level

object Resource {

    const val BACKEND_CREW_FILE_NAME = "backend-crew.md"
    const val FRONTEND_CREW_FILE_NAME = "frontend-crew.md"

    val MissionNames: Map<Level, List<String>> = mapOf(
        Level.LEVEL1 to listOf("자동차경주", "로또", "숫자야구게임"),
        Level.LEVEL2 to listOf("장바구니", "결제", "지하철노선도"),
        Level.LEVEL3 to listOf(),
        Level.LEVEL4 to listOf("성능개선", "배포"),
        Level.LEVEL5 to listOf(),
    )
}
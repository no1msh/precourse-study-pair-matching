package pairmatching

import camp.nextstep.edu.missionutils.test.Assertions.assertShuffleTest
import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import camp.nextstep.edu.missionutils.test.NsTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ApplicationTest : NsTest() {
    @Test
    fun `짝수_인원_페어_매칭`() {
        assertShuffleTest(
            {
                run("1", "백엔드, 레벨1, 자동차경주", "Q")
                assertThat(output()).contains("태웅 : 백호", "치수 : 태섭")
            },
            listOf("태웅", "백호", "치수", "태섭")
        )
    }

    @Test
    fun `없는_미션에_대한_예외_처리`() {
        assertSimpleTest {
            runException("1", "백엔드, 레벨1, 오징어게임")
            assertThat(output()).contains(ERROR_MESSAGE)
        }
    }

    public override fun runMain() {
        main()
    }

    companion object {
        private const val ERROR_MESSAGE = "[ERROR]"
    }
}
package pairmatching.model.shuffler

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import pairmatching.model.data.Course

import pairmatching.model.data.Crew

internal class CrewShufflerTest {

    @Test
    fun `크루순서섞기테스트`() {
        val shuffler = CrewShuffler(ReverseCrewShuffler())
        val crews = makeCrews("성록", "상현", "예린", "수현", "영주")
        val shuffledCrews = makeCrews("영주", "수현", "예린", "상현", "성록").toTypedArray()
        assertThat(shuffler.shuffled(crews)).containsExactly(*shuffledCrews)
    }

    private fun makeCrews(vararg name: String): List<Crew> {
        return name.map { Crew(Course.BACKEND, it) }
    }

    class ReverseCrewShuffler : ICrewShuffler {
        override fun shuffled(crews: List<Crew>): List<Crew> {
            return crews.reversed()
        }
    }
}
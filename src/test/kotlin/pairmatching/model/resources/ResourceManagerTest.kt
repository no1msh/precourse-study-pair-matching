package pairmatching.model.resources

import org.assertj.core.api.Assertions.assertThat

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import pairmatching.model.resources.ResourceManager

internal class ResourceManagerTest {

    @ParameterizedTest
    @ValueSource(strings = ["backend-crew.md", "frontend-crew.md"])
    fun `파일읽기_존재하는파일_notNull`(fileName: String) {
        assertThat(
            ResourceManager.readCrewNames(fileName)
        ).isNotNull
    }

    @ParameterizedTest
    @ValueSource(strings = ["backend-crew2.md", "frontend-crew"])
    fun `파일읽기_존재하지않는_Null`(fileName: String) {
        assertThat(
            ResourceManager.readCrewNames(fileName)
        ).isNull()
    }
}
package pairmatching.model.data.mission

import pairmatching.util.ValueEnum

enum class Level(override val value: String) : ValueEnum<Level> {

    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5"),;

    override fun toString(): String = value

}
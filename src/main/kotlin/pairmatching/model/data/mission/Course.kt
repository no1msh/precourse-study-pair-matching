package pairmatching.model.data.mission

import pairmatching.util.ValueEnum

enum class Course(override val value: String) : ValueEnum<Course> {

    BACKEND("백엔드"),
    FRONTEND("프론트엔드"),;

    override fun toString(): String = value
}
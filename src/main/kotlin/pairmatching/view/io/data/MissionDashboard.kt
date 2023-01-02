package pairmatching.view.io.data

import pairmatching.model.data.mission.Course
import pairmatching.model.data.mission.Level
import pairmatching.model.data.mission.Mission

class MissionDashboard(
    val courses: List<Course>,
    val missions: Map<Level, List<Mission>>,
)
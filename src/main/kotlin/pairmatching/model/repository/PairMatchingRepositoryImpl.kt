package pairmatching.model.repository

import pairmatching.model.data.crew.Crew
import pairmatching.model.data.mission.Course
import pairmatching.model.resources.Resource
import pairmatching.model.resources.ResourceManager

class PairMatchingRepositoryImpl : PairMatchingRepository {

    private lateinit var backendCrews: List<Crew>
    private lateinit var frontendCrews: List<Crew>

    override fun loadCrews(): Boolean {
        backendCrews = readCrewNames(Course.BACKEND) ?: return false
        frontendCrews = readCrewNames(Course.FRONTEND) ?: return false

        return true
    }

    private fun readCrewNames(course: Course): List<Crew>? = when (course) {
        Course.BACKEND -> ResourceManager.readCrewNames(Resource.BACKEND_CREW_FILE_NAME)
        Course.FRONTEND -> ResourceManager.readCrewNames(Resource.FRONTEND_CREW_FILE_NAME)
    }?.map { Crew(course, it) }

}
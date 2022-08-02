class Solution {
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        //In theory, this sounds like a decent candidate for topological sort
        /*
            Corner case: If 1 course, return false (a course can not be a prereq for itself)
            
            Topological Sort:
                - Find any element that does not have any incoming edges
                - Pull that element out
                - Repeat until you can not find any more elements without incoming edges (cycle) or until the container is empty
                
            Iterate over the prereq array, checking the second element in the pair (that's the course that needs the prereq) -> Store all the items within an array 
            -> If an item does not appear within the array, we can start by taking that course, and then adding that course to another data structure to indicate completion
            
            We would have to iterate into the prereq array every time to check (O(n) * O(p) = O(np) time)
            We could cut down on some time by sorting, potentially? By the second element in the array
            We could also cut down on some time be storing the prereq in a different manner, potentially?
        */
        if(prerequisites.size == 0) return true
        if(numCourses == 1) return false
        
        val prereqList = Array<MutableList<Int>>(numCourses) { mutableListOf<Int>() } //Key: Course, Value: List of prereq
        val adjacencyList = Array<MutableList<Int>>(numCourses) { mutableListOf<Int>() } //Key: Course, Value: List courses that require this course
        val queueOfCourses = mutableListOf<Int>()
        prerequisites.forEach { preReqPair ->
            prereqList[preReqPair[1]].add(preReqPair[0])
            adjacencyList[preReqPair[0]].add(preReqPair[1])
        }
        prereqList.forEachIndexed { index, course -> 
            if(course.isEmpty()) {
                queueOfCourses.add(index) //The course itself is the index, value is list of parameters
            }
        }
        while(queueOfCourses.isNotEmpty()) {
            val nextCourse = queueOfCourses.first()
            adjacencyList[nextCourse].forEach { course -> //course is the course that we fulfill *a* requirement for
                val prerequisites = prereqList[course] //prereq is the list of that course's requirements
                var index = 0
                while(index < prerequisites.size) {
                    if(prerequisites[index] == nextCourse) prerequisites.removeAt(index) //remove that requirement, we've taken the course
                    if(prerequisites.isEmpty()) queueOfCourses.add(course) //Take the next course if we can now.
                    index++
                }
            }
            queueOfCourses.removeAt(0)
        }
        var hasAllCoursesBeenTaken = true
        prereqList.forEach { course ->
            if(course.isNotEmpty()) hasAllCoursesBeenTaken = false
        }
        return hasAllCoursesBeenTaken
    }
}
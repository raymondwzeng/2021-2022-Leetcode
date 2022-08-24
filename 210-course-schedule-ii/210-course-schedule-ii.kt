class Solution {
    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        //Course schedule 1 -> Topological sorting problem
        /*
            Topological sorting (this time with printing the order)
            - Create and populate a map <Int, List<Int>> where the key is the course and all items within List<Int> are prerequisites
                - In-degree/edges
            - While the map is not empty: -> O(V), if we tell the algorithm which node is next without scanning
                - For each item within the map, remove the course without prereq if it exists within the list (set) -> O(E)
                    -> If an item now has no items within its set, we can use that element for the next cycle (potentially use a queue)
                - Add the course into some final list
            - Return the list if the map is empty, otherwise empty list.
            
            In this case, leaning towards O(VE) in total, but not sure
            Space: O(V)
            
            Spend a little more space, to also track the outedges and cut down on runtime?
                -> Would mainly help if the # edges on average << # nodes, since # edges <= (# nodes - 1)^2
        */
        val inDegree = Array<MutableSet<Int>>(numCourses) {mutableSetOf<Int>()}
        val outDegree = Array<MutableList<Int>>(numCourses) {mutableListOf<Int>()}
        
        prerequisites.forEach { pair ->
            val inSet = inDegree.get(pair[0]) as MutableSet<Int>
            val outSet = outDegree.get(pair[1]) as MutableList<Int>
            inSet.add(pair[1])
            outSet.add(pair[0])
        }
        
        val returnSet = mutableSetOf<Int>()
        val queue = LinkedList<Int>()
        inDegree.forEachIndexed { index, entry ->
            if(entry.isEmpty()) {
                queue.add(index) //Adds all items which have no indegree
                returnSet.add(index)
            }
        }
        
        while(!queue.isEmpty()) {
            val front = queue.poll()
            outDegree[front].forEach { course ->
               inDegree[course].remove(front) 
                if(inDegree[course].isEmpty() && !returnSet.contains(course)) {
                    queue.add(course)
                    returnSet.add(course)
                }
            }
        }
        
        return if(returnSet.size == numCourses) returnSet.toIntArray() else IntArray(0)
    }
}
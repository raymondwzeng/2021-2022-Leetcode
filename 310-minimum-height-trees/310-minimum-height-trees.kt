/* First attempt (too slow)
    data class RootHeight(val rootNumber: Int, val height: Int)
    
    class RootHeightComparator() : Comparator<RootHeight> {
        override fun compare(a: RootHeight, b: RootHeight): Int {
            return when {
                a.height == b.height -> 0
                a.height < b.height -> -1
                else -> 1
            }
        }
    }
    
    fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {
        //Find the graph that has the smallest maximum BFS - at each node?
        //O(V + E) and we have to run V times, then O(V^2 + VE) would be the naive solution?
        //Maybe add the BFS results into a minstack and just poll until the next value is not the same
        
        //Problem: We need to make sure that we don't iterate over the nodes we've seen before -> Set
        
        val pq = PriorityQueue<RootHeight>(n, RootHeightComparator()) //TODO: Study priority queues
        val adjacencyList = mutableMapOf<Int, MutableList<Int>>()
        val returnList = mutableListOf<Int>()
        
        edges.forEach { pair ->
            adjacencyList.getOrPut(pair[0], {mutableListOf<Int>()}).add(pair[1])
            adjacencyList.getOrPut(pair[1], {mutableListOf<Int>()}).add(pair[0])
        }
        
        // println(adjacencyList)
        
        for(i in 0..n - 1) {
            // println("Starting height calc of $i")
            val queue = mutableListOf<Int>(i)
            val seenSet = mutableSetOf<Int>()
            var height = 0
            
            var nodesAdded = 1
            while(queue.isNotEmpty()) {
                var newNodesAdded = 0
                for(newNodeIndex in 0..nodesAdded - 1) {
                    val front = queue.first()
                    seenSet.add(front)
                    
                    //We want to check and add the adjacencies equal to the number of new nodes that we have added
                    adjacencyList.get(front)?.forEach { adjacency ->
                        if(!seenSet.contains(adjacency)) {
                            // println("Adding node $adjacency")
                            newNodesAdded++
                            queue.add(adjacency)
                        }
                    }
                    
                    queue.removeAt(0)
                }
                if(newNodesAdded > -1) {
                    // println("Added ${newNodesAdded} nodes, increasing height by 1")
                    height++
                }
                nodesAdded = newNodesAdded
            }
            // println("Root at $i has a height of $height")
            pq.add(RootHeight(i, height))
        }
        
        val minHeight = pq.peek().height
        while(!pq.isEmpty() && pq.peek().height == minHeight) {
            returnList.add(pq.poll().rootNumber)
        }
        
        return returnList
    }
*/

class Solution {
    fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {
        if(edges.size == 0) return listOf<Int>(0) //Edge case: Single node without any adjacencies
        val adjacencyList = mutableMapOf<Int, MutableSet<Int>>()
        val returnList = mutableListOf<Int>()
        val seenSet = mutableSetOf<Int>()
        
        edges.forEach { pair ->
            adjacencyList.getOrPut(pair[0], {mutableSetOf<Int>()}).add(pair[1])
            adjacencyList.getOrPut(pair[1], {mutableSetOf<Int>()}).add(pair[0])
        }
        
        val queue = mutableListOf<Int>()
        
        var nodesAdded = 0
        adjacencyList.forEach { entry -> //Initial fill of leaf nodes
            if(entry.value.size == 1) {
                queue.add(entry.key)
                nodesAdded++
                seenSet.add(entry.key)
            }
        }
        
        while(queue.size >= 2) {
            // println("Starting: $queue")
            var newNodesAdded = 0
            for(i in 0..nodesAdded - 1) {
                val front = queue.first()
                val other = adjacencyList[front]?.first()
                // println("Removing 1 from $front, another from $other")
                if(adjacencyList[other]?.size == 1) {
                    if(i == 0) {
                        return queue
                    }
                } else {
                    adjacencyList[front]?.remove(other)
                    adjacencyList[other]?.remove(front)
                }
                if(adjacencyList[other]?.size == 1 && !seenSet.contains(other!!)) {
                    queue.add(other!!)
                    newNodesAdded++
                    seenSet.add(other!!)
                }
                queue.removeAt(0)
            }
            // println(newNodesAdded)
            nodesAdded = newNodesAdded
        }
        
        // println(queue)
        return queue
    }
}
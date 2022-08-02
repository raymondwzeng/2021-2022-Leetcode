/**
 * Definition for a Node.
 * class Node(var `val`: Int) {
 *     var neighbors: ArrayList<Node?> = ArrayList<Node?>()
 * }
 */

class Solution {
    fun cloneGraph(node: Node?): Node? {
        //We probably have to do some sort of graph traversal here
        //The main question I have: How do we avoid adding duplicate nodes, and how do we establish the same adjacencies?
        /*
            Start at some node within the original graph, we'll clone that root
                For each of their connections w/in original graph
                    we need to create that new node -> Only because we know they do not exist
                    (How do we know if some node exists?)
                        -> Recursively calling the node, we can add at least know the origin.
                        -> Value is unique for each node, and the range is 1 to 100.
                        *-> If a node has a value, then it must be the node that needs the connection
                            -> Creating an array of size 100 of node references
                    If a node does not exist, create it and add it to the array.
                        Establish the appropriate connections
                        Add them to the next round of nodes to populate its connections
            
            "BFS-style" approach
            When we visit a node, we will either establish the connections, or create the nodes and establish the connections.
            Therefore, we need to visit all of the nodes at least once to establish those connections.
            
            -> O(n) runtime, where n is the number of nodes in our original graph
            -> O(n) space because of the clone
        */
        if(node == null) return null
        val cloneRoot = Node(node.`val`)
        if(node.neighbors.size == 0) return cloneRoot
        val queueOriginals = mutableListOf<Node>(node)
        val referenceArray = Array<Node?>(100) { null }
        referenceArray[0] = cloneRoot
        
        while(queueOriginals.isNotEmpty()) {
            val front = queueOriginals.first()
            val frontClone = referenceArray[front.`val` - 1]
            front.neighbors.forEach { node ->
                if(node != null) {    
                    if(referenceArray.getOrNull(node.`val` - 1) != null) {
                        frontClone?.neighbors?.add(referenceArray[node.`val` - 1])
                    } else {
                        val newNeighbor = Node(node.`val`)
                        referenceArray[node.`val` - 1] = newNeighbor
                        frontClone?.neighbors?.add(newNeighbor)
                        queueOriginals.add(node)
                    }
                }
            }
            queueOriginals.removeAt(0)
        }
        
        return cloneRoot
    }
}
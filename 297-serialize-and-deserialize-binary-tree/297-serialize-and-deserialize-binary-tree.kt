/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

/** Too slow!
    fun depthOfTree(root: TreeNode?): Int {
        if(root == null) return 0
        val left = if(root?.left != null) depthOfTree(root?.left) + 1 else 1
        val right = if(root?.right != null) depthOfTree(root?.right) + 1 else 1
        return maxOf(left, right)
    }
    
	// Encodes a URL to a shortened URL.
    fun serialize(root: TreeNode?): String { //O(n) operation
        //Layer-order traversal, split with forward slash /, and use n for null (as our inputs are only numbers)
        if(root == null) return ""
        val queue = mutableListOf<TreeNode?>(root)
        val string = StringBuilder()
        var nodesAddedPreviously = 1
        val depth = depthOfTree(root) //Get the depth so that we know exactly how many layers we need to account for (O(n) operation)
        for(layer in 0..depth - 1) {
            val numNodes = Math.pow(2.0, layer.toDouble()).toInt()
            // println("Pulling $numNodes at layer $depth")
            for(nodeCount in 0..numNodes - 1) {
                val front = queue.first()
                string.append("${front?.`val` ?: 'n'}")
                if(layer < depth - 1 || nodeCount < numNodes - 1) string.append("/")
                queue.add(front?.left)
                queue.add(front?.right)
                queue.removeAt(0)
            }
            // println("String now: ${string.toString()}")
        }
        println(string.toString())
        return string.toString()
    }

    // Decodes your encoded data to tree.
    fun deserialize(data: String): TreeNode? {
        val dataList = data.split("/")
        val nodeList = mutableListOf<TreeNode?>()
        
        if(dataList.isEmpty()) return null
        var root : TreeNode? = null
        val numericalRegex = Regex("(-)*([0-9]){1,}")
        
        dataList.forEach { item -> //Converts the list of data into a list of nodes, O(n) operation
            if(item.matches(numericalRegex)) {
                nodeList.add(TreeNode(Integer.parseInt(item)))
            } else {
                nodeList.add(null)
            }
        }
        
        nodeList.forEachIndexed { index, node -> //Matches the nodes to their children, O(n) operation
            if(node != null) {
                val leftCandidate = nodeList.getOrNull(index * 2 + 1)
                val rightCandidate = nodeList.getOrNull(index * 2 + 2)
                if(leftCandidate != null) node?.left = leftCandidate
                if(rightCandidate != null) node?.right = rightCandidate
            }
        }
        
        return nodeList[0]
    }
*/

class Codec() {
    fun serialize(root: TreeNode?): String {
        if(root == null) return ""
        val queue = LinkedList<TreeNode?>()
        queue.add(root)
        val stringBuilder = StringBuilder()
        while(!queue.isEmpty()) {
            val front = queue.poll()
            if(front == null) {
                stringBuilder.append("n")
            } else {
                stringBuilder.append("${front?.`val`}")
                queue.add(front?.left)
                queue.add(front?.right)
            }
            if(!queue.isEmpty()) stringBuilder.append("/")
        }
        return stringBuilder.toString()
    }
    
    fun deserialize(data: String): TreeNode? {
        if(data == "") return null
        val dataList = data.split('/')
        val parentQueue = LinkedList<TreeNode?>()
        val root = TreeNode(Integer.parseInt(dataList[0]))
        parentQueue.add(root)
        var index = 1
        while(index < dataList.size) {
            val parent = parentQueue.poll()
            if(dataList[index] != "n") {
                val leftNode = TreeNode(Integer.parseInt(dataList[index]))
                parent?.left = leftNode
                parentQueue.add(leftNode)
            }
            if(dataList[++index] != "n") { //Move the index to the right as well when checking, because we're done with that node
                //This doesn't run into a index exception because we have the additional layer of padding of nulls.
                val rightNode = TreeNode(Integer.parseInt(dataList[index]))
                parent?.right = rightNode
                parentQueue.add(rightNode)
            }
            index++
        }
        return root
    }
}

/**
 * Your Codec object will be instantiated and called as such:
 * var ser = Codec()
 * var deser = Codec()
 * var data = ser.serialize(longUrl)
 * var ans = deser.deserialize(data)
 */
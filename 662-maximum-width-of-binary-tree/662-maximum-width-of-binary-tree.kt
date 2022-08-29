/*
/*
            BFS/Level Order Traversal, including nulls
            
            For each level
                -> Add the children, or nulls if they do not exist
                -> Pop nodes from the head of the queue until a nonnull node is at the head
                -> Start counting the width between this node and the next non-null node of the layer, or until the layer runs out.
                    -> If there is no other non-null node, then we return the previous maxWidth.
                
            Runtime: O(n) where n is the number of nodes in a complete tree version of the binary tree
            Space: O(n)?
        */
        if(root == null) return 0
        val queue = mutableListOf<TreeNode?>()
        queue.add(root)
        var maxWidth = 0
        while(queue.isNotEmpty()) {
            var originalQueueSize = queue.size
            var numNodesAdded = 0
            var distanceBetweenNodes = 0
            while(queue.first() == null) {
                queue.removeAt(0) //Get rid of the leading nulls
                originalQueueSize--
                queue.add(null)
                queue.add(null)
            }
            for(i in 0..originalQueueSize - 1) {
                val front = queue.first()
                distanceBetweenNodes++
                if(front == null) {
                    queue.add(null)
                    queue.add(null)
                } else {
                    val left = front?.left
                    val right = front?.right
                    if(left != null) {
                        queue.add(left)
                        numNodesAdded++
                    } else {
                        queue.add(null)
                    }
                    if(right != null) {
                        queue.add(right)
                        numNodesAdded++
                    } else {
                        queue.add(null)
                    }
                    
                    if(distanceBetweenNodes > maxWidth) maxWidth = distanceBetweenNodes
                }
                queue.removeAt(0)
            }
            if(numNodesAdded == 0) return maxWidth
        }
        return maxWidth
*/

/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution {
    fun widthOfBinaryTree(root: TreeNode?): Int {
        //We know that in an array representation, the left child will be in position 2i and right will be in 2i + 1, where i = the current index.
        //We will leverage that with our own BFS to calculate the distance between any given pair of nodes.
        if(root == null) return 0
        val nodePosition = mutableMapOf<TreeNode, Int>(root!! to 0)
        var maxWidth = 1
        val queue = mutableListOf<TreeNode>(root!!)
        while(queue.isNotEmpty()) {
            var leftMostIndex = 0
            var rightMostIndex = 0
            val originalSize = queue.size //We need to maintain the original size as a val for our rightMost calculations
            for(index in 0..queue.size - 1) {
                val front = queue.first()
                if(index == 0) leftMostIndex = (nodePosition.get(front) as? Int) ?: 0
                if(index == originalSize - 1) {
                    rightMostIndex = (nodePosition.get(front) as? Int) ?: 0
                }
                if(front.left != null) {
                    queue.add(front.left)
                    nodePosition.put(front.left, ((nodePosition.get(front) as? Int) ?: 0) * 2)
                }
                if(front.right != null) {
                    queue.add(front.right)
                    nodePosition.put(front.right, (((nodePosition.get(front) as? Int) ?: 0) * 2) + 1)
                }
                queue.removeAt(0)
            }
            if(rightMostIndex - leftMostIndex + 1 > maxWidth) maxWidth = rightMostIndex - leftMostIndex + 1
        }
        return maxWidth
    }
}
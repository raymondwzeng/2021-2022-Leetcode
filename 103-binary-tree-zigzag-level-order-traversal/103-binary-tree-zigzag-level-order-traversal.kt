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

/* Working solution 1 in O(n) time, O(n^2) space
val deque = LinkedList<TreeNode>()
        var leftToRight = true
        deque.addFirst(root)
        while(deque.isNotEmpty()) {
            val layerList = mutableListOf<Int>()
            for(index in 0..deque.size - 1) { //For loop acts as a counter
                if(leftToRight == true) {
                    val head = deque.pollFirst()
                    layerList.add(head.`val`)
                    if(head.left != null) deque.addLast(head.left)
                    if(head.right != null) deque.addLast(head.right)
                } else {
                    val tail = deque.pollLast()
                    layerList.add(tail.`val`)
                    if(tail.right != null) deque.addFirst(tail.right)
                    if(tail.left != null) deque.addFirst(tail.left)
                }
            }
            leftToRight = !leftToRight
            returnList.add(layerList)
        }
*/
class Solution {
    fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
        /*
            Use a LinkedList -> Java implementation, DLL
            When we are on an even level, go left to right
            When we are on an odd level, go right to left
            
            Pop and push elements from different ends of the DLL (deque)
            
            O(n) time where n is the number of nodes in the tree
            O(n^2) space, because n^2 is the maximum width of the tree in a perfect tree
            
            For each layer within our return list (starting with the root)
            Read the layer in reverse order (no matter what) and add the elements according to the leftToRight boolean
            Problem: Until we reach the last layer and add no other nodes from the next layer, we don't know when to stop
                -> We add more calls (+1 layer)
                
            Runtime same as above solution --> Realistically a bit slower
            O(1) space
        */
        if(root == null) return emptyList<List<Int>>()
        var depth = 0
        val returnList = mutableListOf<List<TreeNode>>(listOf<TreeNode>(root))
        
        while(depth < returnList.size) {
            val list = returnList.get(depth)
            var newLayerList = mutableListOf<TreeNode>()
            for(index in list.size - 1 downTo 0) {
                val currentNode = list.get(index)
                if(depth % 2 == 0) {
                    //Iterate over the layer RTL for the next layer --> Reverse order
                    if(currentNode.right != null) newLayerList.add(currentNode.right)
                    if(currentNode.left != null) newLayerList.add(currentNode.left)
                } else {
                    //Iterate over the layer LTR for the next layer --> Reverse order, but left child first 
                    if(currentNode.left != null) newLayerList.add(currentNode.left)
                    if(currentNode.right != null) newLayerList.add(currentNode.right)
                }
            }
            if(newLayerList.isNotEmpty()) returnList.add(newLayerList)
            depth++
        }
        
        return returnList.map { internalList -> //List of tree nodes
            internalList.map { item -> item.`val` } //Returns a List of Integers
        } //Returns a list of list of integers
    }
}
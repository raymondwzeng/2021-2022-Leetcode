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

/*
    (Slow) Recursive Method
    
            val layerList = mutableListOf<List<Int>>(listOf(root.`val`))
        var leftList = emptyList<List<Int>>()
        var rightList = emptyList<List<Int>>()
        if(root.left != null) {
            leftList = levelOrderAddToList(root.left)
        }
        if(root.right != null) {
            rightList = levelOrderAddToList(root.right)
        }
        if(!leftList.isEmpty()) {
            if(!rightList.isEmpty()) {
                //Neither Empty
                if(leftList.size > rightList.size) {
                    for(index in 0..leftList.size - 1) {
                        val list : List<Int> = leftList[index]
                        rightList.getOrNull(index)?.let { right ->
                            layerList.add(list + right)
                        } ?: layerList.add(list)
                    }
                } else {
                    for(index in 0..rightList.size - 1) {
                        val list : List<Int> = rightList[index]
                        leftList.getOrNull(index)?.let {left -> 
                            layerList.add(left + list)
                        } ?: layerList.add(list)
                    }
                }
            } else {
                //Right Empty
                leftList.forEach { layer -> layerList.add(layer) }
            }
        } else if (!rightList.isEmpty()) { //Left Empty
            rightList.forEach { layer -> layerList.add(layer) }
        } //Don't do anything if both empty
        return layerList
*/

class Solution {
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        //Sounds like BFS! But on a tree, instead of...well a graph
        /*
            Reminder: BFS is not a recursive function like DFS! You use a queue.
            
            Approach
            
                Start at the root
                For each layer, create a new list (or queue)
                For the current list that you have, go from left to right, and add children in the order in which they appear
                Once you're done, add the list to the queue of lists
                
            If n is the number of nodes in the tree, then arguably, O(n)
        */
        if(root == null) return listOf<List<Int>>()
        
        val returnList = mutableListOf<List<Int>>()
        val traversalList = mutableListOf<List<TreeNode>>(listOf(root))
        
        while(!traversalList.isEmpty()) {
            val front = traversalList.first()
            val nextLayerList = mutableListOf<TreeNode>()
            front.forEach { node -> 
                if(node.left != null) nextLayerList.add(node.left)
                if(node.right != null) nextLayerList.add(node.right)
            }
            if(!nextLayerList.isEmpty()) traversalList.add(nextLayerList)
            returnList.add(front.map{ node -> node.`val` })
            traversalList.removeAt(0)
        }
        return returnList
    }
}
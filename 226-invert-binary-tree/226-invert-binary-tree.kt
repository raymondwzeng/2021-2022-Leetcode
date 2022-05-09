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
    fun invertTree(root: TreeNode?): TreeNode? {
        //The idea is to build an inorder list, and then "flip" the values.
        //An inorder list can be built by creating a queue of queues(?).
        root?.let { //Only do if root is not null, thanks KT
            var DoubleQueue = ArrayDeque<List<TreeNode>>() //A bit jank, maybe use Lists and MutableLists for now until KT updates.
            var start = listOf<TreeNode>(root)
            DoubleQueue.add(start)
            while(!DoubleQueue.isEmpty()) {
                var nextDeque = mutableListOf<TreeNode>()
                val innerDeque = DoubleQueue.pop().toMutableList()
                while(innerDeque.size != 0) {
                    var left = innerDeque.take(1).firstOrNull()
                    left?.run { innerDeque.remove(left) }
                    var right = innerDeque.takeLast(1).firstOrNull()
                    right?.run { innerDeque.remove(right) }
                    left?.let {
                        it.left?.run { nextDeque.add(it.left) }
                        it.right?.run { nextDeque.add(it.right) }
                        val temp = it.left
                        it.left = it.right
                        it.right = temp
                    }
                    right?.let {
                        it.left?.run {
                            nextDeque.add(it.left)
                        }
                        it.right?.run {
                            nextDeque.add(it.right)
                        }
                        val temp = it.left
                        it.left = it.right
                        it.right = temp
                    }
                }
                
                if(!nextDeque.isEmpty()) DoubleQueue.add(nextDeque)
            }
        }
        return root
    }
}
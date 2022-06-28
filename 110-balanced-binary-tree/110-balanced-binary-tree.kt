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
import kotlin.math.abs

class Solution {
    fun depth(root: TreeNode?) : Int {
        if(root == null) return 0
        val leftDepth = if(root.left != null) 1 + depth(root.left) else 0
        val rightDepth = if(root.right != null) 1 + depth(root.right) else 0
        when {
            leftDepth == 0 && rightDepth == 0 -> return 1
            leftDepth > rightDepth -> return leftDepth
            else -> return rightDepth
        }
    }
    
    fun isBalanced(root: TreeNode?): Boolean {
        //Let's try to use DFS again, but this time strictly comparing left and right subtrees
        if(root == null) return true
        return (abs(depth(root.left) - depth(root.right)) <= 1) && isBalanced(root.left) && isBalanced(root.right)
    }
}
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
    fun maxDepth(root: TreeNode?): Int {
        //Binary tree traversal 
        if(root == null) return 0
        val leftDepth = maxDepth(root.left) + 1
        val rightDepth = maxDepth(root.right) + 1
        if(leftDepth > rightDepth) return leftDepth else return rightDepth
    }
}
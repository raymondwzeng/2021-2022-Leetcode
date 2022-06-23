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
        //Attempting some bastardized DFS recursive method
        //This works because we just end up swapping the leaves, then working our way up, basically backwards vs the first solution.
        if(root == null) return null
        if(root.left != null) invertTree(root.left)
        if(root.right != null) invertTree(root.right)
        val temp = root.left
        root.left = root.right
        root.right = temp
        return root
    }
}
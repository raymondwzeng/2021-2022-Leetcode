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
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        //Run a specific check to see if the nodes are the same -> Traversal 
        //Recursive-like solution -> A tree is the same as another tree if and only if the left and right subtrees are identical. i.e if we check the current node, the left subtree, and the right subtree, and they are all the same, then we have the same tree.
        when {
            p == null && q == null -> return true
            p != null && q != null -> {
                val sameValue = p.`val` == q.`val`
                val leftSame = if(p.left != null || q.left != null) isSameTree(p.left, q.left) else true
                val rightSame = if(p.right != null || q.right != null) isSameTree(p.right, q.right) else true
                return sameValue && leftSame && rightSame
            }
            else -> return false
        }
    }
}
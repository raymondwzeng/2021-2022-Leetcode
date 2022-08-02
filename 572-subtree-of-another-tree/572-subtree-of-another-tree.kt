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
    fun isSameTree(root: TreeNode?, subRoot: TreeNode?): Boolean {
        if((root == null && subRoot != null) || (root != null && subRoot == null)) return false
        if(root == null && subRoot == null) return true
        if(root?.`val` == subRoot?.`val` && root?.left == null && subRoot?.left == null && root?.right == null && subRoot?.right == null) return true
        return root?.`val` == subRoot?.`val` && isSameTree(root?.left, subRoot?.left) && isSameTree(root?.right, subRoot?.right)
    }
    
    fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
        //2 components -> First find all plausible nodes that we can evaluate, then check if the tree rooted at that point is the same
        if(root == null && subRoot != null) return false
        if(root?.`val` == subRoot?.`val`) {
            if(isSameTree(root, subRoot)) return true
        }
        if(root?.left == null && root?.right == null && subRoot != null) return false
        return isSubtree(root?.left, subRoot) || isSubtree(root?.right, subRoot)
    }
}
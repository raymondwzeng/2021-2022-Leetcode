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
    var lastVal: Int? = null
    
    fun inOrderTraversal(root: TreeNode?): Boolean {
        if(root == null) return true
        var leftValid = true
        var rightValid = true
        if(root.left != null) leftValid = inOrderTraversal(root.left)
        val centerValid = lastVal == null || root.`val` > lastVal!!
        lastVal = root.`val`
        if(root.right != null) rightValid = inOrderTraversal(root.right)
        return centerValid && leftValid && rightValid
    }
    
    fun isValidBST(root: TreeNode?): Boolean {
        //Run a postorder traversal through the tree
        //How do we check to make sure that each element is where it should be, without making it too slow?
        
        //Could we just do a sliding window of size 2? (Comparing against previous neighbor [previous value])
        //How do we pick the max of the left side, and the min of the right side efficiently? O(2^n), maybe solution
        //We could sort -> If the order of any element changes, then we know that the BST is invalid O(nlogn) solution
        //We need to compare a singular element against all other elements...right?
        
        //No, the inorder was right
        if(root?.left == null && root?.right == null) return true
        lastVal = null
        return inOrderTraversal(root)
    }
}

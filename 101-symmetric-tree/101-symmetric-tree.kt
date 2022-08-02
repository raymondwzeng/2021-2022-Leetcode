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
    fun isSymmetric(leftTree: TreeNode?, rightTree: TreeNode?): Boolean {
        if(leftTree?.left == null && leftTree?.right == null && rightTree?.left == null && rightTree?.right == null) return leftTree?.`val` == rightTree?.`val`
        return leftTree?.`val` == rightTree?.`val` && isSymmetric(leftTree?.left, rightTree?.right) && isSymmetric(leftTree?.right, rightTree?.left)
    }
    
    fun isSymmetric(root: TreeNode?): Boolean {
        //Level Order Traversal (BFS) [Iterative]
        //  -> Check if the list is a palindrome
        if(root?.left == null && root?.right == null) return true
        val layerList = mutableListOf<MutableList<TreeNode?>>(mutableListOf(root?.left, root?.right))
        while(!layerList.isEmpty()) {
            val currentLayer = layerList.get(0)
            var leftReference = 0
            var rightReference = currentLayer.size - 1
                while(leftReference < rightReference) {
                if(currentLayer[leftReference]?.`val` != currentLayer[rightReference]?.`val`) return false
                if(currentLayer[leftReference]?.left != null || currentLayer[leftReference]?.right != null || currentLayer[rightReference]?.left != null || currentLayer[rightReference]?.right != null) {
                    val newQuad = mutableListOf(currentLayer[leftReference]?.left, currentLayer[leftReference]?.right, currentLayer[rightReference]?.left, currentLayer[rightReference]?.right)
                    layerList.add(newQuad)   
                }
                leftReference++
                rightReference--
                }
            layerList.removeAt(0)
        }
        return true
        
        //Recursive approach
        //  -> A tree is symmetric if the left child of the left subtree is equal to the right child of the right subtree, and vice-versa (right of left = left of right)
        // return isSymmetric(root?.left, root?.right)
    }
}
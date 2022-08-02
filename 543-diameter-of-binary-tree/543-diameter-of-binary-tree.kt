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
    var longestPath = 0
    
    fun preOrder(root: TreeNode?) : Int { //The "score" of the leftmost node
        if(root == null) return 0
        var leftScore = 0
        var rightScore = 0
        var currentNode = root!!
        if(currentNode.left != null) {
            leftScore = preOrder(currentNode.left) + 1
        }
        if(currentNode.right != null) {
            rightScore = preOrder(currentNode.right) + 1
        }
        if(leftScore + rightScore > longestPath) longestPath = leftScore + rightScore
        if(leftScore > rightScore) return leftScore else return rightScore
    }
    
    fun diameterOfBinaryTree(root: TreeNode?): Int {
        //Preorder traversal towards the left
        //Keep track of longest path globally through a variable, return the longest single path to see if we want to include our parent node for a longer path
        longestPath = 0
        preOrder(root)

        return longestPath
    }
}
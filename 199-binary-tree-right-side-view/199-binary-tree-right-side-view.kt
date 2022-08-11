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
    fun rightSideView(root: TreeNode?, depth: Int, list: MutableList<Int>) {
        if(root == null) return
        if(depth >= list.size) {
            list.add(root.`val`)
        }
        if(root.right != null) rightSideView(root.right, depth + 1, list)
        if(root.left != null) rightSideView(root.left, depth + 1, list)
    }
    
    fun rightSideView(root: TreeNode?): List<Int> {
        /*
            Level order traversal (favoring the right side) -> Take the right element
            Postorder traversal -> Only print out/add the most deep element on the right of the layer
            
            O(n) time, where n is the number of nodes within our tree
            
            Depth is defined in this case as # edges between the node and the root
        */
        if(root == null) return emptyList<Int>()
        val rightSide = mutableListOf<Int>(root.`val`)
        rightSideView(root, 0, rightSide)
        return rightSide
    }
}
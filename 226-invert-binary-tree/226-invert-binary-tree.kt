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
        //The weird kicker here is that when we invert the binary tree, the children don't change, but are rather swapped. 
        //This one approach that we could do is a level-order traversal and reversal, in which we add the root, swap their children and add them to the next stack over.
        if(root == null) return null
        val rootStack = listOf(root)
        val stack: MutableList<List<TreeNode>> = mutableListOf<List<TreeNode>>(rootStack)
        while(!stack.isEmpty()) {
            val layer = stack.get(0)
            //Get the first list in the list of lists
            val nextLayerList = mutableListOf<TreeNode>()
            layer.forEach { node ->
                val temp = node.left
                node.left = node.right
                node.right = temp
                if(node.left != null) nextLayerList.add(node.left)
                if(node.right != null) nextLayerList.add(node.right)
            }
            if(!nextLayerList.isEmpty()) stack.add(nextLayerList)
            stack.removeAt(0)
        }
        return root
    }
}
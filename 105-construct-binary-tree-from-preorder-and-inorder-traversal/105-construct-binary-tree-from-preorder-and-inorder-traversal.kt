/** Old Comments
                 3
                /                          3
              10      (a)  or (b)         / \
             /                            10 8
            8
           / \
          9  7
            a)
            Preorder: [(3, 10, 8), 9, 7] 10 could be on left or right, 8 could be in 4 places, but it doesn't matter (2 choices)
            Inorder: [(9, 8, 7), 10, 3]
            
            b)
            Preorder: [3, 10, 8]
            Inorder: [10, 3, 8]
        
            [3, 9, 20, 15, 7] (preorder [print when traversing node])
            -> Node, left, right
            -> Node, left, ?
            -> Node, right, ?
            [9, 3, 15, 20, 7] (inorder [print after left node traversal])
            -> Left, node, right
            -> Node, right, ?
            -> Left, node, ?
        
            Two pointers -> Look at the numbers within the preorder and inorder, if certain conditions hold, then we can create the node and the left/right.
            Maybe a sliding window of size 3?
            
            1st node in preorder will be the root
            Assuming preorder is node, left, right, then the next node is on the left (or should be), and node after is on the right.
            Main issue here -> We don't necessarily know if the tree has a bunch of nulls or not.
            
            The inorder traversal has the opposite with left, node, right, which guarantees that the node to the right is the parent.
            
            Main question: In what cases of pre and in pointers, will we guarantee that some node is on the left/right of another?
            
            If after the first node, if the pre and in pointers are the same, then we know that node is on the left
            Otherwise, that inorder's node's parent is the preorder? (No, if more than 3 layers, is false)
            
            [Else] If the preorder's next is equal to inorder now, then we know that it is a parent, but what about the direction?
            Check if there exists a number 2 away from inorder now, and if that is after preorder.next, then current is left child, that is right child. Otherwise, that next preorder is the right child?
*/

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
    var rootIndex: Int = 0 //Not a fan of stateful classes, but we sort of need it
    
    fun buildTree(preorder: IntArray, inorder: IntArray, leftEdge: Int, rightEdge: Int, indexMap: Map<Int, Int>): TreeNode? {
        if(leftEdge >= rightEdge) return null
        val root = TreeNode(preorder[rootIndex])
        val inorderIndex = indexMap.get(preorder[rootIndex++])!!
        root.left = buildTree(preorder, inorder, leftEdge, inorderIndex, indexMap)
        root.right = buildTree(preorder, inorder, inorderIndex + 1, rightEdge, indexMap)
        return root
    }
    
    /*
        The core intuition of the problem is that every node in preorder happens to either be a root of another subtree, or a leaf.
        What about the left and right subtrees?
        Well, we are given the inorder traversal, which means that the left subtree are predecessors, and right subtree are successors.
        
        Why does this work with the indexes?
        BECAUSE of the preorder traversal, we know that the next root (be it left or right) will be traversed next. Thus we can simply increment over to the next item in our list, and build up the tree from there
        
        How do we know left vs right subtree?
        Suppose that we have no left subtree, and a right subtree instead.
        Root calls left first, building from leftEdge to rootIndex - 1 within the inorder traversal.
        If a left subtree does not exist, then there will be no additional elements to the left of our root, that we haven't visited yet.
        Thus leftEdge >= rightEdge (rootIndex - 1)
        A similar justification can be established for the right subtree.
    */
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        val indexMap = mutableMapOf<Int, Int>()
        preorder.forEach { root ->
            indexMap.put(root, inorder.indexOf(root))            
        }
        
        return buildTree(preorder, inorder, 0, inorder.size, indexMap)
    }
}
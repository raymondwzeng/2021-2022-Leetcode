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
    var counter = 0
    
    fun kthSmallest(root: TreeNode): Int {
        var leftReturn = -1
        var rightReturn = -1
        if(root.left != null) leftReturn = kthSmallest(root.left)
        if(counter == 1) {
            counter-- //Reduce the counter here so that we do not end up returning additional values
            return root.`val`
        }
        counter-- //Otherwise, just reduce the counter in general
        if(root.right != null) rightReturn = kthSmallest(root.right)
        return if(leftReturn != -1) leftReturn else rightReturn
    }
    
    fun kthSmallest(root: TreeNode?, k: Int): Int {
        //10^4 input -> O(n) runtime
        /*
            Could we construct some sort of ordering? Inorder traversal -> O(n) time, then get the kth smallest element
            
            There could be some sort of "trick" where you could traverse down the tree and eliminate a certain number of elements.
            -> You could need to know the # of nodes in each subtree
            
            We can get the min and max of the BST relatively easily
                -> Counting from the min, we know roughly the number of nodes that *could* preceed us
                
            If we could store the number of nodes at each subtree, we could probably run a traversal to record all of the depths,
            and then use that number to traverse to the nth smallest.
        */
        if(root == null) return -1
        counter = k
        return kthSmallest(root)
    }
}
/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int = 0) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

/*

/This could probably done as a BST traversal.
        /*
            We know that all elements are unique, and that it's a BST.
            Thus we can use some BST traversal to find both p and q.
            
            The naive solution would be to store BOTH p and q ancestors, comparing them at the end. But would that really be so bad?
        */
        val pAncestors = mutableListOf<TreeNode>()
        val qAncestors = mutableListOf<TreeNode>()
        
        var pFound = false
        var qFound = false
        //We are given that p and q exist, so no need for edge case handling within this problem.
        //Naive solution: Use 2 separate traversals
        var currNode = root!!
        while(!pFound) {
            pAncestors.add(currNode)
            when {
                currNode.`val` < p!!.`val` -> currNode = currNode!!.right
                currNode.`val` > p!!.`val` -> currNode = currNode!!.left
                else -> pFound = true
            }
        }
        currNode = root
        while(!qFound) {
            qAncestors.add(currNode)
            when {
                currNode.`val` < q!!.`val` -> currNode = currNode!!.right
                currNode.`val` > q!!.`val` -> currNode = currNode!!.left
                else -> qFound = true
            }
        }
        pAncestors.asReversed().forEach { node -> //Since the nodes will be added in the ordered that they are discovered, reversing the list means that the lowest ancestors go first, guaranteeing that we will retun them
            if(qAncestors.contains(node)) {
                return node
            }
        }
        return null //None found, nothing to return basically
    }
    
7% runtime, 5% space. Too slow.
*/

class Solution {
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        /*
            Alternative approach:
            - Keep track of the current ancestor. When we diverge, that's our LCA.
            - This works because we don't have duplicates, AND it's a BST (so its location must be right at the fork)
        */
        var currNode = root
        while(currNode != null) {
        when {
            currNode.`val` > p!!.`val` && currNode.`val` > q!!.`val` -> currNode = currNode.left
            currNode.`val` < p!!.`val` && currNode.`val` < q!!.`val` -> currNode = currNode.right
            else -> return currNode
            }
        }
        return null
    }
}
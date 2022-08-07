/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int = 0) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

/* Failed attempt
            fun buildSet(root: TreeNode?, nodeSet: MutableSet<Int>) {
        if(root == null) return
        if(root.left != null) buildSet(root.left, nodeSet)
        nodeSet.add(root.`val`)
        if(root.right != null) buildSet(root.right, nodeSet)
    }
    
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?, left: Set<Int>?, right: Set<Int>?): TreeNode? {
        if(root == null) return null
        if(p == null || q == null) return null
        
        lateinit var leftSet: Set<Int>
        lateinit var rightSet: Set<Int>
        
        when {
            left == null && right == null -> {
                leftSet = mutableSetOf<Int>()
                buildSet(root.left, leftSet)
                rightSet = mutableSetOf<Int>()
                buildSet(root.right, rightSet)
            }
            left == null && right != null -> {
                leftSet = mutableSetOf<Int>()
                buildSet(root.left, leftSet)
                rightSet = right - leftSet
            }
            left != null && right == null -> {
                rightSet = mutableSetOf<Int>()
                buildSet(root.right, rightSet)
                leftSet = left - rightSet    
            }
            else -> {
                throw Exception("Invalid state")
            }
        }
        
        when {
            root == p && (leftSet.contains(q.`val`) || rightSet.contains(q.`val`)) -> return p
            root == q && (leftSet.contains(p.`val`) || rightSet.contains(p.`val`)) -> return q
            (leftSet.contains(p.`val`) && rightSet.contains(q.`val`)) || (leftSet.contains(q.`val`) && rightSet.contains(p.`val`)) -> return root
            (leftSet.contains(p.`val`) && leftSet.contains(q.`val`)) -> return lowestCommonAncestor(root.left, p, q, leftSet, null)
            else -> return lowestCommonAncestor(root.right, p, q, null, rightSet)
        }
    } 

        //Recursion
        //LC Easy inspiration -> Lowest Common Ancestor of a Binary Search Tree
        //  -> If a number is between p and q (inclusive), then it is the LCA
        //  -> If a number is greater than both, then an LCA exists which is less than the current number
        //  -> Similarly, if less, then LCA exists which is greater
        
        /*
            What problems exist without a natural order invariant?
                - We don't know where p and q are (but they exist)
                - They could both be in the left or right subtrees from a given node, or they could be split up.
        
            Brute Force Solution
            -> Run a traversal across the left subtree, and the right subtree, to produce a set of both sides.
            -> Compare to see if both are on the left, both are on the right, or one exists per side.
            -> If both are on the left, or both are on the right, then we can update our root/LCA to that root, and continue on.
            
            First iteration (root) O(n/2) + O(n/2) -> O(n), where n is the number of nodes in the tree.
            Second iteration O(n/4) + O(n/4) -> O(n)
            And so on...
            Until we reach the leaves (or until we find the LCA)
            
            Amortized, an argument could be made for O(1), but realistically, probably O(n)
            
            Space will probably be around O(n) as well
            
            Repeated work: Rebuilding our sets for each iteration down our tree.
            Within our tree, if we recurse on one side, then there will only really be one node which is removed -> Root
            
            Why not turn our set into a list?
                -> .contains should be constant time (vs. linear)
                -> Remove should also be doable in constant time
                
            How can we determine what is on the left subtree vs. the right subtree of the set?
            
            Suppose that p and q exist on the left subtree. What information can we get from our current set?
                -> Remove the item with the value that matches the subtree
                    -> If we know the node, we know at least the items on its left and right
        */
        return lowestCommonAncestor(root, p, q, null, null)

*/
class Solution {
    
    fun findAncestors(root: TreeNode?, target: TreeNode, ancestorSet: MutableSet<TreeNode>): TreeNode? {
        if(root == null) return null
        if(root.left != null) {
            val left = findAncestors(root.left, target, ancestorSet)
            if(left != null) {
                ancestorSet.add(root)
                return root
            }
        }
        if(root.right != null) {
            val right = findAncestors(root.right, target, ancestorSet)
            if(right != null) {
                ancestorSet.add(root)
                return root
            }
        }
        if(root.`val` == target.`val`) {
            ancestorSet.add(root)
            return root
        }
        return null
    }
    
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        /*
            Rather than going top-down (searching within the entire set of nodes), go bottom-up (search for the nodes, and compare ancestors)
        */
        if(root == null) return null
        if(p == null || q == null) return null
        val pSet = mutableSetOf<TreeNode>()
        findAncestors(root, p, pSet)
        val qSet = mutableSetOf<TreeNode>()
        findAncestors(root, q, qSet)
        //How do we know which ancestor is the lowest?
        val shorterSet = if(pSet.size < qSet.size) pSet else qSet
        val otherSet = if(shorterSet == pSet) qSet else pSet
        shorterSet.forEach { ancestor ->
            if(otherSet.contains(ancestor)) {
                return ancestor
            }
        }
        return null
    }
}
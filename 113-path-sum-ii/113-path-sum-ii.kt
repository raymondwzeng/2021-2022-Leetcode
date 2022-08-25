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
    fun pathSum(root: TreeNode?, targetSum: Int, currentPath: List<Int>): List<List<Int>> {
        if(root == null) return emptyList<List<Int>>()
        if(root.left == null && root.right == null) { //Leaf node
            if(root.`val` == targetSum) return listOf<List<Int>>(currentPath + root.`val`) else return emptyList<List<Int>>()
        }
        val returnList = mutableListOf<List<Int>>()
        if(root.left != null) {
            val leftResult = pathSum(root.left, targetSum - root.`val`, currentPath + root.`val`)
            if(leftResult.isNotEmpty()) leftResult.forEach { list -> returnList.add(list) }
        }
        if(root.right != null) {
            val rightResult = pathSum(root.right, targetSum - root.`val`, currentPath + root.`val`)
            if(rightResult.isNotEmpty()) rightResult.forEach { list -> returnList.add(list) }
        }
        return returnList
    }
    
    fun pathSum(root: TreeNode?, targetSum: Int): List<List<Int>> {
        /*
            Probably a recursive solution
            
            Inorder traversal until leaves
            Parameters: root: TreeNode?, targetSum: Int, currentSum: Int, currentPath: List<Int>, return List<List<Int>>?
            
            Suppose that we are at a given root R
                If(currentSum + root.`val` > targetSum), we don't even need to bother checking the rest of the tree
                If the root is null, then we also return an empty list
                If Root has children:
                    Traverse down those children on the left and right
                        -> If the child list returned is not empty, then add current root.`val` and return that list
                        -> We need List<List<Int>> because if both sides of the tree rooted at root can have a sum, then we need to return both
                If root does not have children:
                    If the currentSum + root == targetSum, return a list with root.`val` inside
                    Otherwise return an empty list
                    
            At the end, we need to go through each list and reverse them, or we could consider...
            Exchanging space for time: Pass in a duplicated list by reference and append to that, requires up to O(n) space for the lists
            By passing in the list rather than creating it from the bottom up, we remove the need to reverse each list after we return.
            
            O(n) time, O(n) space where n is the number of nodes within our tree.
            
            Start with 5
                - Recurse on 4 with target 17, current path [5]
                    - Recurse on 11 with target 13, current path [5, 4]
                        - Recurse on 7 with target 2, current path [5, 4, 11] -> Returns empty, 7 is leaf != 2
                        - Recurse on 2 with target 2, current path [5, 4, 11] -> Returns [5, 4, 11] + [2] -> Returns up to 11, then 4, then 5
                - Recurse on 8 with target 17, current path [5]
                    - Recurse on 13 with target 9, current path [5, 8] -> Returns empty 13 is leaf != 9
                    - Recurse on 4 with target 9, current path [5, 8, 4]
                        - Recurse on 5 with target 5, current path [5, 8, 4] -> Returns [5, 8, 4] + [5] -> Returns up
                        - Recurse on 1 with target 5 -> Returns empty 1 is leaf != 5
        */
        return pathSum(root, targetSum, emptyList<Int>())
    }
}
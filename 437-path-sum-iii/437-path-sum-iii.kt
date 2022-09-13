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
    fun pathSum(root: TreeNode, targetSum: Int, sumMap: Map<Int, Int>): Int {
        val complement = targetSum - root.`val`
        val newSumMap = mutableMapOf<Int, Int>()
        val numPathsTerminatingHere = if(sumMap.contains(complement)) sumMap.get(complement)!! else 0
        sumMap.forEach { entry ->
            val newSum = root.`val` + entry.key //The key is the actual value, the value is the frequency
            if((root.`val` > 0 && entry.key > 0 && newSum > root.`val`) || (root.`val` < 0 && entry.key < 0 && newSum < root.`val`) || (root.`val` >= 0 && entry.key <= 0) || (root.`val` <= 0 && entry.key >= 0)) { //Overflow/underflow protection
                newSumMap.put(newSum, entry.value) //All paths that can add up to the given value can now go through this node. 
            }
        }
        newSumMap.put(root.`val`, (newSumMap.get(root.`val`) ?: 0) + 1) //We can start a new path at our current node
        val leftAmount = if(root.left != null) pathSum(root.left, targetSum, newSumMap.toMap()) else 0
        val rightAmount = if(root.right != null) pathSum(root.right, targetSum, newSumMap.toMap()) else 0
        return numPathsTerminatingHere + leftAmount + rightAmount + if(targetSum == root.`val`) 1 else 0
    }
    
    fun pathSum(root: TreeNode?, targetSum: Int): Int {
        /*
            Differences from PS2
            - Don't just start at the root, end at the leaf
            - KEEP the same number of nodes [0, 1000]
            
            Backtracking is likely off the table -> O(2^n) or similar, which is too slow for >= 10
            
            Recursive solution
                - Given a value input (parent) and our current node value, return +1 if a path exists here.
                - We have a lot of branches if we want to calculate every possibility...or maybe 2?
                    -> Some exponential blowup still if we go this route (previous 2 + start current)
                    
            Hashmap problem (combine segments with the same sum to produce an outcome)
            Dynamic programming problem
            
            For each branch, we pass in a hashmap that indicates all of the possible previous sums (along with their frequency)
            
            Maximum number of elements within that sum hashmap is 1 + 2 + 3 + 4 + ... + h -> O(h^2), where h is the height of the current node
            Since the height of the tree is bounded by log_2(h), then the number of elements within the algorithm for the hashmap is O(log_2(h^2)) => O(h)?
            
            We would need to find the number that matches, and return the number of elements that it works with => O(1), going through each element O(n) where n is the number of elements within the tree
            
            What happens in an array representation of the tree?
        */
        if(root == null) return 0
        return pathSum(root, targetSum, emptyMap<Int, Int>())
    }
}
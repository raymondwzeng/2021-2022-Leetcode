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
    fun sortedArrayToBST(nums: IntArray, left: Int, right: Int): TreeNode? {
        if(right < left) return null
        if(right - left == 0) {
            // println("Returning ${nums[right]} as a single node")
            return TreeNode(nums[right])
        }
        if(right - left == 1) {
            val rightNode = TreeNode(nums[right])
            val leftNode = TreeNode(nums[left])
            rightNode.left = leftNode
            // println("Returning ${rightNode.`val`} with a left of ${leftNode.`val`}")
            return rightNode
        }
        val middle = (right + left + 1) / 2
        val middleNode = TreeNode(nums[middle])
        if(right - left == 2) {
            // println("Returning ${middleNode.`val`} with left and right")
            middleNode.left = TreeNode(nums[left])
            middleNode.right = TreeNode(nums[right])
            return middleNode
        }
        // println("Left: $left")
        // println("Right: $right")
        // println("Middle: ${middle}")
        
        // println("Left half is $left to ${middle}")
        // println("Right half is ${middle + 2} to ${right}")
        middleNode.left = sortedArrayToBST(nums, left, middle - 1)
        middleNode.right = sortedArrayToBST(nums, middle + 1, right)
        // println("Left has ${middleNode.left?.`val`} and right of ${middleNode.right?.`val`}")
        return middleNode
    }
    
    
    fun sortedArrayToBST(nums: IntArray): TreeNode? {
        /*
            Left to right, since the number will always be "bigger" in terms of BST...
            
            -> If no root exists, create the new node and have that as the root
            -> @ the third item, we can either place it as the root.right, or as the new root.
            
            Start with the root, which will always be (in a balanced BST) in the middle of the array.
            Then, we just use our standard BST insertion algorithm
            
            (If duplicates were allowed, we would need some other way besides value checking to skip the middle)
            
            Creation of the root -> O(1) to find the middle of an array
            Going through each node and adding it -> O(log(n)) for each insertion, n times.
            
            Total time: O(n) * O(logn) -> O(nlogn)
            
            index mod 3 = 1 -> Most recent middle (left or right)
            In the case that we don't have one of those, then we need to consider maybe a left
            [0, 1, 2, 3, 4, 5]
            [L, M, R, Rt, L, M]
        */
        return sortedArrayToBST(nums, 0, nums.size - 1)
    }
}
class Solution {
    fun searchRecursive(nums: IntArray, target: Int, low: Int, high: Int): Int {
        if(low > high || low < 0 || high >= nums.size || nums.size == 0) return -1
        val midpoint = (low + high) / 2
        if(nums[midpoint] == target) return midpoint
        if(nums[midpoint] < target) return searchRecursive(nums, target, midpoint + 1, high) //+1 to midpoint is needed to avoid infinite loop with truncation
        return searchRecursive(nums, target, low, midpoint - 1)
    }
    
    fun search(nums: IntArray, target: Int): Int {
        return searchRecursive(nums, target, 0, nums.size - 1)
    }
}
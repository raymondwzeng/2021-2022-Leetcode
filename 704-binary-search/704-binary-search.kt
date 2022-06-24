class Solution {
    fun search(nums: IntArray, target: Int, low: Int, high: Int) : Int {
        val mid = (low + high) / 2
        if(low > high || low < 0 || high > nums.size || mid > nums.size - 1) return -1 //Default cases.
        when {
            nums[mid] == target -> return mid
            nums[mid] < target -> return search(nums, target, mid + 1, high)
            else -> return search(nums, target, low, mid - 1)
        }
    }
    
    fun search(nums: IntArray, target: Int): Int {
        return search(nums, target, 0, nums.size)
    }
}
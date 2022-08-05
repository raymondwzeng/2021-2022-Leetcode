class Solution {
    
    fun search(nums: IntArray, target: Int, left: Int, right: Int): Int {
        val mid = (right + left) / 2
        if(left >= right) return -1 //Not sure if this need to be updated
        if(nums[mid] == target) return mid
        println("Left: $left, Right: $right")
        if(nums[left] < nums[mid]) { //a
            if(target >= nums[left] && target < nums[mid]) {
                return search(nums, target, left, mid)
            } else {
                return search(nums, target, mid + 1, right)
            }
        } else { //b
            if(target > nums[mid] && target <= nums[right - 1]) {
                return search(nums, target, mid + 1, right)
            } else {
                return search(nums, target, left, mid)
            }
        }
        return -1
    }
    
    fun search(nums: IntArray, target: Int): Int {
        //This, given its O(logn) runtime expectation, is probably a nuanced application of binary search
        /*
            Suppose that we are pivoted around a nonstandard index
                - Still check the middle in relation to the left and right sides, there are two outcomes from this
                a. Left < Mid -> Check to see if the number falls within the range of the "normal half", otherwise check the other
                b. Mid < Right -> Check to see if the number falls within the range of the "normal half", otherwise check the other
                
                
            O(logn) runtime, O(1) space
        */
        return search(nums, target, 0, nums.size)
    }
}
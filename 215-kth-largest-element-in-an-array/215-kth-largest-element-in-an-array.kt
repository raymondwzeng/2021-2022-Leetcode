class Solution {
    fun findKthLargest(nums: IntArray, k: Int, left: Int, right: Int): Int { //Left, right exclusive of right
        var lowBlock = left
        val pivot = right - 1
        for(index in left..(right - 1)) {
            if(nums[index] < nums[pivot]) {
                val temp = nums[index]
                nums[index] = nums[lowBlock]
                nums[lowBlock++] = temp
            }
        }
        //Swap the pivot into place
        val temp = nums[pivot]
        nums[pivot] = nums[lowBlock]
        nums[lowBlock] = temp
        
        if(nums.size - lowBlock == k) {
            return nums[lowBlock]
        } else if (lowBlock < nums.size - k) { //Too small, move up 
            return findKthLargest(nums, k, lowBlock + 1, right)
        } else { //Too large, move down
            return findKthLargest(nums, k, left, lowBlock)
        }
    }
    
    fun findKthLargest(nums: IntArray, k: Int): Int {
        //Quickselect
        /*
            QuickSelect
                - "Parititon" part of the quicksort algorithm
                - Intuition is that we pick an element, and pivot items to the left or right of it based on its size
                - We know based on our pivot's position, which half we need to continue to partition
            O(n) time
        */
        return findKthLargest(nums, k, 0, nums.size)
    }
}
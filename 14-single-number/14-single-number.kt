class Solution {
    fun singleNumber(nums: IntArray): Int {
        if(nums.size == 1) return nums[0]
        nums.sort() //Assume this runs a radix sort, which is linear
        //Run a sliding window of size...2?
        
        //Possible conditions to think about
        /* (assuming 1, 2, and 3)
            
            11, or 22, or 33 (whatever)
            12 or 23 (or something else)
            11223 -> Handled
            12233 -> Handled
            11233 -> Handled
                -> Single number will show up on the left if it is indeed the first, OR on the right
                -> If the numbers don't line up and the sliding window's left edge is on the first element, return that element
                -> Otherwise, note down a flag for the number, and if it is the single number again, then return that.   
        */
        var flaggedNumber = Integer.MIN_VALUE
        for(i in 0..nums.size - 2) {
            val leftEdge = i
            val rightEdge = i + 1
            when {
                nums[leftEdge] != nums[rightEdge] -> {
                    if(leftEdge == 0) return nums[leftEdge] //Special case
                    if(rightEdge == nums.size - 1) return nums[rightEdge] //Special case 2
                    if(nums[leftEdge] == flaggedNumber) {
                        return nums[leftEdge] //We've seen the number before in a discrepancy, now return it
                    } else {
                        flaggedNumber = nums[rightEdge]
                    }
                }
                else -> flaggedNumber = Integer.MIN_VALUE
            }
        }
        return -1
    }
}
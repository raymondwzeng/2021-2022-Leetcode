class Solution {
    fun moveZeroes(nums: IntArray): Unit {
        /* Naive solution: We swap one at a time with the nearest nonzero
            [0, 1, 0, 3, 12] 
            [1, 0, 0, 3, 12] -> 1 scan
            [1, 3, 0, 0, 12] -> 2 scans
            [1, 3, 12, 0, 0] -> 2 scans (Total 5 scans)
            
            Problem: If we had first half 0s and second half nonzeros, this would repeat a scan of a lot of zeros
            Repeat work from the scans
            
            [0, 0, 1, 3, 12]
            [1, 0, 0, 3, 12] -> 2 scans
            [1, 3, 0, 0, 12] -> 2 scans
            [1, 3, 12, 0, 0] -> 2 scans
            
            Possible solution: Keep a reference to the next index to check for a nonzero number
        
            [0, 0, 1, 3, 12] -> (next initializes at 0)
            [1, 0, 0, 3, 12] -> 2 scans (next = 3)
            [1, 3, 0, 0, 12] -> 0 scans (next = 4)
            [1, 3, 12, 0, 0] -> 0 scans (next = 5) -> return from there because > length - 1
            
            
            [1, 3, 12, 0, 0] -> 3 scans to hit the 0 + 2 scans to hit over the array, then return?
            
            [0, 1, 0, 3, 0, 12] -> Hit 0 on 0, 1 scan to hit 1, swap, nextIndex = 2
            [1, 0, 0, 3, 0, 12] -> Hit 0 on 1, 1 scan to hit 3, swap, nextIndex = 4
            [1, 3, 0, 0, 0, 12] -> Hit 0 on 2, 1 scan to hit 12, swap, nextIndex = 5
            [1, 3, 12, 0, 0, 0]
            
            Problem 2: We have additional scans 
        */
        if (nums.size == 1) return //Nothing to do whether the first value is 0 or nonzero
        var nextIndex = 1
        nums.forEachIndexed { index, value ->
            if(value == 0) {
                if(nextIndex < index) nextIndex = index + 1 //Just in case we don't find a nonzero for a while
                var hasSwapped = false
                while(nextIndex < nums.size && nums[nextIndex] == 0 && !hasSwapped) nextIndex++
                        if(nextIndex <= nums.size - 1) { //We found a suitable nonzero to still swap with
                            val temp = value
                            nums[index] = nums[nextIndex]
                            nums[nextIndex] = temp
                            nextIndex++
                            hasSwapped = true
                        } else {
                            return //No more nonzeros to swap with
                        }
            }
        }
    }
}
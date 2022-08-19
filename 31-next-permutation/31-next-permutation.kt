class Solution {
    fun nextPermutation(nums: IntArray): Unit {
        //Nums.length <= 100 -> O(n^3) solution, potentially?
        //Swap the numbers within nums so that it is the next permutation
        //Generating all permutations would be way too slow -> 4 items max
        
        //Something that is kind of like a quicksort?
        //Go through each number, and swap it into place
        
        /*
            [1, 2, 3]
            [1, 3, 2] -> Swap the middle and the right side (which is larger)
                [1, 2, 3] (intermediate)
                [2, 1, 3]
            [2, 1, 3] -> Swap the first and the last (the min of the right side?)
            [2, 3, 1] -> Swap the middle and right side (which is larger)
                [2, 1, 3] (intermediate)
                [3, 1, 2]
            [3, 1, 2] -> Swap the first and the middle (the min of the right side that is still > first)
            [3, 2, 1] -> Swap the middle and right side (which is larger) [2 is in the right place] (special provision for the max?)
                [3, 1, 2] (intermediate)
                [1, 3, 2] (incorrect)
                            Maybe swap with the leftmost item (less than itself) if we can not find a value
        
            Questions: How do you know which tile to swap? -> Left MUST be smaller than the right side, UNLESS there is no greater number
                (in which case you swap with the smallest, because wraparound)
            
            # of permutations that are possible given n numbers? -> Are they equally distributed across each number? -> Probably no
            
            [1, 1, 5]
            [1, 5, 1]
            [5, 1, 1]
            
            Maybe start from the end of the array, and for each index -> O(n)
            Try to find the minimum number which is still greater than you -> 1 + 2 + 3 + ... + n operations, which is O(n^2) * O(n)
            If one exists, then swap with it, and we're done
            Otherwise, if we can not find a larger number to swap with, then we need to swap with the minimum number, assuming that the swap will occur at another digit
            
            [1, 2, 3, 4]
            [1, 2, 4, 3]
            [1, 3, 2, 4]
            [1, 3, 4, 2]
            ..
            [4, 3, 1, 2]
            [4, 3, 2, 1] -> Maybe just reverse it then? Maybe identifiable by if we have no valid swaps (decreasing)
            
            [5,4,7,5,3,2]
            [5,4,7,5,2,3] -Swapped 3 and 2
            [5,4,7,2,5,3] -Swapped 5 and 2
            [5,4,2,7,5,3] -Swapped 7 and 2
            [5,5,2,7,5,3] -Swapped 4 and 5
            
            [5,4,7,5,3,2]
            [5,4,7,5,2,3] -Still swap 3 and 2
            [5,4,7,3,2,5] -Swap 5 and 3 (rightmost lesser)
            [5,4,5,3,2,7] -Swap 7 and 5 (rightmost lesser)
            [5,5,4,3,2,7]
            
            --Go from right to left
            --Swap with the minimum of larger numbers
            --Make sure the order of the window from that side is ascending.
            
        */
        if(nums.size == 1) return
        //Start with the reversal check
        var isMax = true
        var currentMin = nums[0]
        nums.forEach { num ->
            if(num > currentMin) {
                isMax = false
            } else { 
                currentMin = num 
            }
        }
        if(isMax == true) {
            nums.reverse()
            return
        }
        
        for(reverseIndex in nums.size - 2 downTo 0) {
            var minOfGreater = -1 //Minimum of the numbers larger than reverseIndex
            for(index in reverseIndex + 1..nums.size - 1) {
                if(nums[index] > nums[reverseIndex]) {
                    if(minOfGreater == -1 || (minOfGreater != -1 && nums[index] <= nums[minOfGreater])) {
                        minOfGreater = index
                    }
                }
            }
            if(minOfGreater != -1) { //Check to see if we can either swap with a larger number and return, OR swap with the minimum and continue
                // println("Swapping ${nums[reverseIndex]} and ${nums[minOfGreater]}")
                val temp = nums[reverseIndex]
                nums[reverseIndex] = nums[minOfGreater]
                nums[minOfGreater] = temp
                
                //Reverse the subarray starting at the next index which is in descending order so that it is in ascending order.
                var left = reverseIndex + 1
                var right = nums.size - 1
                while(left < right) {
                    val temp = nums[left]
                    nums[left] = nums[right]
                    nums[right] = temp
                    left++
                    right--
                }
                return
            }
        }
    }
}
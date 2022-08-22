class Solution {
    fun findDuplicate(nums: IntArray): Int {
        //Cyclic sort
        /*
            Iterate through each number in the array
            -> Swap the item with the item currently in its rightful place
            -> If the item in its place is the same, then that is the duplicate
            -> Otherwise, continue with the swapping.
            
            [1, 3, 4, 2, 2] -> 1st swap does nothing, 1 is in its place
            [1, 4, 3, 2, 2] -> Swap 3 into place
            [1, 2, 3, 4, 2] -> Swap 2 into place
            -> We see that 2 is in its place, and we want to swap it with 2 again, so that is the duplicate, return 2.
            
            [3, 1, 3, 4, 2] -> 1st swap here sees a 3 in its place, return 3.
            
            O(n) time, where n is the number of items in the array
            O(1) space
            
            If I swapped in (n - k) items previously (correctly), then I would only need to swap (n - (n - k)) elements -> the remaining elements
        */
        var index = 0
        while(index < nums.size) {
            if(nums[index] == index + 1) {
                index++
            } else {
                val current = nums[index]
                val other = nums[current - 1]
                if(current == other) return current
                nums[current - 1] = current
                nums[index] = other
            }
        }
        return nums.size - 1
    }
}
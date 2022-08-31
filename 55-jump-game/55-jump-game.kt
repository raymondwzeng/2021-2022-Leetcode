class Solution {
    fun canJump(nums: IntArray): Boolean {
        //Whether or not we can jump to the next block is contingent on whether or not we can jump to any previous block that can reach us.
        /*
            Backwards DP setup
            
            - Create a boolean array of the same size as nums (the last item is set to true)
            - For each item starting at the 2nd to last to the beginning
                - Check to see if there are any elements which we can jump to which have a value of true
                - If yes, we can set our own value to true, and move on
                - Otherwise, we can not reach the end from that tile
            - Return the boolean value at index 0
            
            O(n^2) time, O(n) space
            
            Suppose it was not possible to reach any element (i.e [2, 1, 0, 0])
            If we could not reach the end, we would still have to exhaust the entire value of the inner loop (which can be as much as n - k, where k = nums[i])
            
            n <= 10^4, which signal an O(n) or O(nlogn) solution
            
            If DP is too slow, then there may be a greedy solution
            Is there a way that we could check all elements after our current in O(1) time?
                - If (n - 1) - i <= nums[i], then we can reach the last element without going through intermediate elements
                - Could we optimize the case where it's not? (Bits/bit manipulation?)
                [0, 0, 0*, 0, 1]
                [0, 0, 0, 1, 0] AND
                == 0
                
                [2, 3, 1, 1, 4]
                [0, 0*, 1, 1, 1]
                [0, 0, 1, 1, 0] AND
                != 0
        */
        val canReachArray = BooleanArray(nums.size){false}
        canReachArray[nums.size - 1] = true
        for(index in nums.size - 2 downTo 0) {
            if((nums.size - 1) - index <= nums[index]) canReachArray[index] = true
            var innerIndex = index + 1
            while(innerIndex < nums.size && innerIndex - index <= nums[index] && !canReachArray[index] == true) {
                if(canReachArray[innerIndex] == true) canReachArray[index] = true
                innerIndex++
            }
        }
        return canReachArray[0]
    }
}
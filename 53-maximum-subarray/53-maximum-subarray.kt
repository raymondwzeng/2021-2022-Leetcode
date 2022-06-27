class Solution {
    fun maxSubArray(nums: IntArray): Int {
        //The first naive approach would be to simply run a bunch of sliding windows.
        //The smart approach would likely involve dynamic programming...
        //There is apparently also a solution that involves D&C, which implies that we do NOT have overlapping subproblems, in which case...no DP?
        
        /*
            Fundamentally, we have only 2 choices at EACH junction which will be the same.
            Since we are dealing with a subarray, we don't have the mega exp. blowup that we would have
            for like a subsequence type beat.
            
            Thus, create 2 arrays - one where we record the value if we restarted at the current tile, and one if continued taking them. We will always keep the new value, on the restart. On the take, however, we have the choice between taking from our previous restart + new, or old take + take.
            
            Essentially, 3 choices.
        */
        //No need to consider 0 size arrays, not in problem
        var resetMax = nums[0]
        var takeMax = nums[0]
        var reset = nums[0]
        var take = nums[0]
        nums.forEachIndexed { index, num ->
            if(index > 0) {
                if(reset + num > take + num) {
                    take = reset + num
                } else {
                    take = take + num
                }
                if(takeMax < take) takeMax = take
                reset = num //Set the resetMax to the current number (reset now)
                if(resetMax < reset) resetMax = reset //In case we have a max from resetting only (1 positive in sea of negative)
            } 
        }
        return if(resetMax > takeMax) resetMax else takeMax
    }
}
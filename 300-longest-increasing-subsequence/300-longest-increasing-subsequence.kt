class Solution {
    fun lengthOfLIS(nums: IntArray): Int {
        /*
            Traditionally, this is solved with dynamic programming
            -> Each element has two subproblems -> We either start the sequence at our current number (1), or we continue our subsequence from a previous number (for all elements prior?)
            Comparison would be if the number itself is < current number, and chainSize (number) > max so far
            -> O(n^2) runtime
            
            What if we wanted to do it in O(nlogn) time?
                -> We don't want to change the order of the remaining elements, so no sorting at all.
                -> Kth-style approach with quickselect is out
                -> Maybe some sort of binary tree?
        */
        val longestChainArray = IntArray(nums.size) { 1 }
        var maxChainSeenTotal = 1
        nums.forEachIndexed { index, num ->
            var maxChainSoFar = 1
            for(i in 0..index - 1) {
                if(nums[i] < num && longestChainArray[i] + 1 > maxChainSoFar) maxChainSoFar = longestChainArray[i] + 1
            }
            longestChainArray[index] = maxChainSoFar
            if(maxChainSoFar > maxChainSeenTotal) maxChainSeenTotal = maxChainSoFar
        }
        return maxChainSeenTotal
    }
}
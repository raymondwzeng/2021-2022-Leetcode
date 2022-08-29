/* Good first attempt
        //Longest contiguous array with the same number of 1s and 0s
        /*
            Sliding window question
                -> Start with the sliding window of size 1
                -> While the numbers are not equal, grow the array
                -> While the numbers are equal, shrink from the back (left)
                
            Dynamic Programming
                -> At each point, we decide whether or not to continue the previous array, or to start again at that particular index
                -> Length of current cell = maxOf(all i from 0..index - 1 where the number of 0s and 1s are not balanced and adding current would make it balanced)
                -> Maybe we only need to check n-1 and n-2
                    -> Say we had a balanced array at n - 1, then we can't add onto it, we must have an inbalanced array to continue that
                    -> If we instead had a balanced array at n - 2, then we must make sure that n != n-1
                    -> Otherwise, the value at that array is 1 (restart?)
                O(n^2) runtime
                
            [0, 0, 1, 0, 1]
            [F, F, F, F, F] -> Balance Array?
            [F, F, T, F, F]
            [F, F, T, T, F] //Previous value == opposite of current
            [1, 1, 1, 1, 1] -> DP Array
            [1, 1, 2, 1, 1]
            [1, 1, 2, 2, 1] //We could either have a length of 2 (balanced) or length of 3 (imbalanced)
            //Take a value in the hopes that it gets balanced?
            
            [0, 0, 0, 1, 1, 1]
            [1, 2, 3, 2, 1, 0] -> +1 if == 0, -1 if == 1
            At every point, could potentially be the start of a new subarray
            
            [0, 0, 1, 0, 1]
            [1, 2, 1, 2, 1] -> We don't have a starting point
               [1, 0, 1, 0]
                
                
            [1, 1, 0, 0, 1, 0]
            Combinations that we have
            1x0, 1x0 + 1x1, 1x1, 2x0 + 1x1
            2 edge cases
                - If nums.length == 1 return 0
                - If nums.length == 2 return 2 if (nums[0] == 0 && nums[1] == 1) || (nums[0] == 1 && nums[1] == 0)
            
            nums.length <= 10^5 -> O(n) solution
            
            [0, 0, 1, 0, 1]
            [1, 2, 1, 2, 1] -> Point Array, store the start value of 0
            [0, 1, 0, 1, 0] //If both halves of the subproblems reach 0, then we can join them together and still maintain evenness
            
            [0, 0, 0, 1, 1, 1]
            [1, 2, 3, 2, 1, 0]
            [1, 1, 1, 0, 0, 0]
                
            Length must be an even number (for hopefully obvious reasons)
            
        */
*/

class Solution {
    fun findMaxLength(nums: IntArray): Int { //[0, 0, 1] -> 1 -> 2 -> 1
        //We can take the "score" idea to its conclusion - we care about when the values are the same, as when they are, we can combine the halves to make a balanced subarray! They don't need to be exactly at 0.
        if(nums.size == 1) return 0
        if(nums.size == 2) return if((nums[0] == 1 && nums[1] == 0) || (nums[0] == 0 && nums[1] == 1)) 2 else 0
        var maxLength = 0
        var score = 0
        val scoreMap = mutableMapOf<Int, Int>()
        scoreMap.put(0, -1)
        nums.forEachIndexed { index, num -> //Go through each number within the array and keep the current score
            if(num == 0) {
                score++
            } else {
                score--
            }
            if(scoreMap.get(score) != null) { //The first index of count will always be the longest, so no need to update afterwards
                val oldIndex : Int = scoreMap.get(score) as? Int ?: Integer.MAX_VALUE
                if(index - oldIndex > maxLength) maxLength = index - oldIndex //The length of the array is the difference between the indices
            } else {
                scoreMap.put(score, index)
            }
        }
        return maxLength
    }
}
/* Close!
        //Sliding window approach
        //Nums.size <= 2 * 10^4 -> O(nlogn) or O(n) solution
        //If O(nlogn) -> Sorting to maybe gain some advantage -> Can't do this with subarrays
        
        /*
            Naive solution
                -> Check all subarrays to see if they equal k
                -> O(n^2) time to check all subarrays
                -> If checking all subarrays is too slow, sliding window can help
                    -> We know the middle amount will not change, only that we will add the right side and subtract left side
                    
            Sliding Window Solution
                -> Start with a subarray of size 1
                -> While Left hand side is less than/equal to right?
                -> While the running sum is less than k and RHS is less than size, move the right side over and increase window size
                    -> If the window equals k increment value
                    -> Shrink the left hand side
                    
            O(n) time, O(1) space
            
            Do we need to do something to account for negative values?
            [1, -1, 1, 1, 2] k = 2
            
            [1, -3, 1, 1, 2] k = 3
            
            
            The problem is that we deal with a negative value that we can't really move around
        */
        if(nums.size == 0) return if(nums[0] == k) 1 else 0
        var left = 0
        var right = 0
        var runningSum = nums[0]
        var subarrays = 0
        while(left <= right) {
            while(right < nums.size - 1 && runningSum < k) runningSum += nums[++right]
            println("$left, $right")
            if(runningSum == k) subarrays++
            runningSum -= nums[left++]
        }
        return subarrays
*/
class Solution {
    fun subarraySum(nums: IntArray, k: Int): Int {
        //Hashmaps may be an approach to achieve linear runtime where sliding window fails.
        //Consider that as an option the next time.
        //This solution kind of feels like 2Sum, where we store the value and the number of times we've seen that value before.
        //If we encounter a case where we have the sum - k value, then we know that we can make an array...
        //Why -> Sum only increases, if we have a value from before, then our subarray is the elements that made up the previous value along with the values that made up from there to current sum.
        //We will also store the frequency in the map and increment by that amount
        //Why -> If we have a frequency that makes sense for the first instance, then we can extend the array to the next instance + our current subarray, and so on. Therefore, we need to add the number of occurrences (before incrementing)
        //****Sliding window doesn't really work when negative values are thrown into the mix.
        
        /*
            Solution at a high level (thanks to addy_boy)
            - For any given subarray, our sumEnd = sumBegin + x, where sumBegin is the sum we start with before the array, x is the sum inside, and sumEnd is the sum afterwards.
            - Since we're looking specifically for x = k, then sumEnd = sumBegin + k
            - Thus, sumBegin = sumEnd - k
            - What this means for us is that by searching for a starting point where sumBegin is sumEnd - k, we KNOW that since sumEnd - sumBegin = k, then the subarray works.
            - Furthermore, if it works for this instance, then it will also work for any other previous sumBegin' where sumBegin' == sumBegin
        */
        val frequencyMap = mutableMapOf<Int, Int>() //Where the key is the value, and the value is the number of times it has shown up.
        frequencyMap.put(0, 1) //Is this necessary?
        var sum = 0
        var numSubarrays = 0
        nums.forEach { num ->
            sum += num
            val complement = sum - k
            if(frequencyMap.get(complement) != null) {
                val amount = frequencyMap.get(complement) as Int
                numSubarrays += amount
            }
            frequencyMap.put(sum, frequencyMap.getOrPut(sum, {0}) + 1)
        }
        return numSubarrays
    }
}
/* Decent first attempt
        //10^5 -> O(n) runtime, k <= 10^5
        //Using O(1) space
        //Potentially 3 different ways
        
        /*
            Naive solution:
                - For each item from 0..n
                    - Take a temp value of the 2nd
                    - Set the 2nd value to the first
                    - Set the first to temp
                    - Move the pointers forward
                    -* If we are at the end, then move the 2nd pointer to the 0th index
                
                O(nk) time, O(1) space
                
                Repeated work?
                    - Maybe we can move the item to its final location in 1 swoop
                    - Every item is moved (k mod n) units to the right
                
                Thought: Could we use a while loop to go through all of the swaps until we reach the initial starting location
                No, example 2 disproves this
                
                Could we use a for loop in some way to swap all of the items?
                
                Better(?) solution:
                - For each item from 0..n
                    - Replace the current item with the first, and add the current item into a queue
                    - If we reach the point where values can be replaced without going under 0, then replace the upcoming index (index + k mod n) with the head of the queue.
                    
                O(n) time, O(n/2) space
                
                "Leapfrogging"
                - For each item from 0..n
                    - Replace the current item with the first, and hold on to the current item.
                    - While we are not back at the original index, continue to replace the newer indices (index + k mod n)
                    
                Problem: How do we know when we've gone to the point where we have already replaced items and should not swap again?
                Potential runtime: O(n) time, O(1) space
                
                [1, 2, 3, 4] k = 4 -> [1, 2, 3, 4] (if k == n, return)
                [1, 2, 3, 4, 5] k = 2
                [1, 2, 1, 4, 5] t = 3
                [1, 2, 1, 4, 3] t = 5
                [1, 5, 1, 4, 3] t = 2
                [1, 5, 1, 2, 3] t = 4
                [4, 5, 1, 2, 3] t = 1
                
                [1, 2, 3, 4, 5] k = 3
                [1, 2, 3, 1, 5] t = 4
                [1, 4, 3, 1, 5] t = 2
                [1, 4, 3, 1, 2] t = 5
                [1, 4, 5, 1, 2] t = 3
                [3, 4, 5, 1, 2]
                
                [1, 2, 3, 4, 5, 6] k = 3
                [1, 2, 3, 1, 5, 6] t = 4
                [4, 2, 3, 1, 5, 6] t= 1
                ...
                
                [1, 2, 3, 4, 5, 6] k = 4
                [1, 2, 3, 4, 1, 6] t = 5
                [1, 2, 5, 4, 1, 6] t = 3
                [3, 2, 5, 4, 1, 6] t = 1
                [3, 2, 5, 4, 1, 2] t = 6
                [3, 2, 5, 6, 1, 2] t = 4
                [3, 4, 5, 6, 1, 2] t = 2
                
                Indices affected: 0, 2, 4
                Indices affected: All the remaining ones...
                
                Maybe twice if even or if exactly n / 2, once if odd?
                
                If k == n / 2 then we need to iterate until n / 2
                Otherwise, maybe just once?
                
                Another solution:
                - Start with a set
                - Go from k until k - 1 (inclusive, mod n)
                - Add items into the set
                - For each item within the set, set that value into the IntArray

                O(n) time, O(n) space
        */
        if(k == nums.size || nums.size == 1) return
        var tempValue = nums[0]
        var index = k % nums.size
        var firstTime = true
        while(index != 0) {
            val tempTempValue = nums[index]
            nums[index] = tempValue
            tempValue = tempTempValue
            index = (index + k) % nums.size
            nums.forEach {num -> print(num)}
            println()
        }
        nums[0] = tempValue
        nums.forEach {num -> print(num)}
        println()
        if(k % 2 == 0 || k.toDouble() == (nums.size / 2.0)) {
            tempValue = nums[1]
            index = (k + 1) % nums.size
            while(index != 1) {
                val tempTempValue = nums[index]
                nums[index] = tempValue
                tempValue = tempTempValue
                index = (index + k) % nums.size
            }
            nums[1] = tempValue
        }
        return
*/

class Solution {
    fun rotate(nums: IntArray, k: Int): Unit {
        //The CORE idea is that we need to move the items from k..n - 1 to the front of the array
        //A more efficient way to do this is to reverse the partition from [0, k] and [k + 1, n - 1], then reverse the entire array.
        var newMod = k % nums.size
        nums.reverse()
        reverse(nums, 0, newMod - 1)
        reverse(nums, newMod, nums.size - 1)
    }
    
    /*
        Reverses a range from [begin] to [end], inclusive of both.
    */
    fun reverse(nums: IntArray, begin: Int, end: Int) {
        var left = begin
        var right = end
        while(left < right) {
            val temp = nums[left]
            nums[left] = nums[right]
            nums[right] = temp
            left++
            right--
        }   
    }
}
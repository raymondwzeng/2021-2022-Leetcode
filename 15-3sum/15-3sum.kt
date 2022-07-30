class Solution {
    fun threeSum(nums: IntArray): List<List<Int>> {
        //Two pointer pattern
        /*
            Approach
            
            For each num in nums -> O(n)
                "Pin" the current index
                For each number in the two pointer between the number's next value and the end of the array
                    Check to see if the combination is first unique, and then adds up to zero.
                        Can we establish uniqueness without checking all of our existing combinations?
                        Yes, but we need to do something else first -> Sorting
                        When we move the pointers up (and iterate through the array), by having a sorted list rather than an unsorted one, then we can't use the "pin" for the same combination twice
                    If true, then add combination to list. If not, move the two pointers
                (n - 1) + (n - 2) + ... + 1 -> O(n^2)
            
            In any case, we know that sorting is O(nlogn) with quicksort, and since we probably won't "beat" that time with our algorighm, we can do sorting for "free"
            
            (O(n) * O(n^2)) + O(nlogn) = O(n^3)
            
            [-4, -1, -1, 0, 1, 2]
        */
        
        nums.sort()
        val listOfLists = mutableListOf<List<Int>>()
        
        nums.forEachIndexed { index, num ->
            if(index < nums.size - 2 && ((index > 0 && num != nums[index - 1]) || index == 0)) {
                var left = index + 1
                var right = nums.size - 1
                while(left < right) {
                    when {
                        num + nums[left] + nums[right] == 0 -> { //Completely relies on the fact that our array is sorted
                            listOfLists.add(listOf(num, nums[left], nums[right]))
                            while(left < right && nums[left + 1] == nums[left]) { left++ }
                            while(left < right && nums[right - 1] == nums[right]) { right-- }
                            left++
                            right--
                        }
                        num + nums[left] + nums[right] < 0 -> left++
                        else -> right-- 
                }
            }
        }
    }
        return listOfLists
    }
}
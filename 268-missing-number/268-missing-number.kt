class Solution {
    fun missingNumber(nums: IntArray): Int {
        //Use a set, collect the numbers in nums, then check which number is missing
        /*
            for each number in nums, add to a set, and keep track of a maximum -> O(n)
            for each number in 0 to the max, check if it exists within the set -> O(n)
            
            Runtime: O(n), Space: O(n)
        */
        
        //Sorting using Radix sort (which is close enough to linear), then using a sliding window
        /*
            sort the array by number order (least to greatest)
            using a sliding window of size, check to see if the difference between the left and right numbers is 1
                -> Corner case 1: If the number that is missing is the first number [1, 2, 3], check that the first number is always 0
                -> Corner case 2: If the number at the end is NOT equal to nums.size, then the last number is missing.
                
                
            [0, 1, 2, 4]
            Runtime: O(n) runtime, Space: O(1)
        */
        // if(nums.size == 1) {
        //     if(nums[0] == 1) return 0
        //     return 1
        // }
        // nums.sort()
        // if(nums[0] != 0) return 0 //Corner case 1
        // for(i in 0..nums.size - 2) {
        //     val left = i
        //     val right = i + 1
        //     if(nums[right] - nums[left] != 1) return (nums[right] + nums[left])/2
        // }
        // return nums.size //Corner case 2
        
        /*
            A potential mathematical solution
            Theory: If we add up all of the numbers (and get the size), we can get 2 values:
                1. The sum of our numbers within our array
                2. The potential maximum *if* all elements within the array are present. (n * (n + 1)/2)
                
            For each number in nums, add it up.
            Calculate the potential maximum using nums.size
            Return the difference
            
            Runtime: O(n) runtime, O(1) space
        */
        val currentSum = nums.fold(0, {current, newValue -> current + newValue})
        val potentialSum = (nums.size * (nums.size + 1))/2
        return potentialSum - currentSum
    }
}
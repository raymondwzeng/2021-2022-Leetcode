/* Scuffed attempt 1

 //Sorting -> Will that help? -> Bounded by a best case scenario of O(nlogn)
        //Two pointers (probably not) -> We need the info of the numbers between the edges
        //Sliding window -> Maybe?
        
        /*
            Potential partial solution
            -> If the number is not even, we know that it isn't possible
            1. Sort the array
            2. Put two partitions/sliding windows at each end of the array
            3. Compare the values 
                -> If the left side is < right side, increment left side and add the value
                -> If the left side is > right side, shrink by 1 on the left, and add that to the right?
                
                [1*, 2, 3, 5*] l = 1, r = 5
                [1, 2*, 3, 5*] l = 3, r = 5
                [{1, 2, 3*}, {5*}] l = 6, r = 5 -> If the smallest number in the left hand side is equal to the difference + 1
                [1}, {2, 3*}, {5*] l = 5, r = 6
                
                What is our terminating condition?
                
                [1, 5, 7, 11] l = 1, r = 11
                [1, 5*, 7, 11*] l = 6, r = 11
                [{1, 5, 7*}, {11*}] l = 13, r = 11 -> If the smallest number on LHS <= difference, then subtract from left window, add to right window
                [1}, {5, 7*}, {11*] l = 12, r = 12
                
                [{1, 3, 10*}, 10*] l = 1, r = 4
                
                If we need both subsets to be equal, then that means that each subset needs to be exactly sum/2
                Then the question really is - can we produce two sets that have those amounts?
                
                [3*, 3, 3, 4, 5*]
                [{3, 3, 3*}, 4, 5*]
                
                [[1, 1*], 2, [2*]]
                
                [2}, {3, 4, 8, 9*}, {14*] l = 24, r = 16
        */
        if(nums.size == 1) return false
        if(nums.size == 2) return nums[0] == nums[1]
        val sum = nums.fold(0) { accumulator, value -> accumulator + value }
        if(sum % 2 != 0) return false
        
        nums.sort()
        
        var leftWindowLeft = 0
        var leftWindowRight = 0
        var leftSum = nums[leftWindowRight]
        var rightIndex = nums.size - 1
        var rightSum = nums[rightIndex]
        
        while(leftWindowRight < rightIndex) {
            // println("Left Sum: $leftSum, Right Sum: $rightSum")
            // println("LWL: $leftWindowLeft, LWR: $leftWindowRight, rI: $rightIndex")
            if(leftSum < rightSum) {
                if(leftWindowRight + 1 < rightIndex) {
                    leftWindowRight++
                    leftSum += nums[leftWindowRight]
                } else return false
            } else if(leftSum > rightSum) {
                if(rightIndex - 1 > leftWindowRight) {
                    rightIndex--
                    rightSum += nums[rightIndex]
                } else if(nums[leftWindowLeft] <= leftSum - rightSum) {
                    rightSum += nums[leftWindowLeft]
                    leftSum -= nums[leftWindowLeft]
                    leftWindowLeft++
                } else return false
            } else {
                if(leftWindowRight + 1 == rightIndex) return true
                if(rightIndex - 1 == leftWindowRight + 1) {
                    leftWindowRight++
                    leftSum += nums[leftWindowRight]
                } else {
                    rightIndex--
                    leftWindowRight++
                }
            }
        }
        
        return false
*/

class Solution {
    fun canPartition(nums: IntArray): Boolean {
        if(nums.size == 1) return false
        if(nums.size == 2) return nums[0] == nums[1]
        val sum = nums.fold(0) { accumulator, value -> accumulator + value }
        if(sum % 2 != 0) return false
        
        val arr = BooleanArray(sum + 1) //We create this because then we can reference by the raw number instead of offsetting
        arr[0] = true //This array represents "can we partition if sum is x", where x is the index.
        
        nums.forEach { num -> //Do this for each number, where we choose to either take (arr[index - num]) or leave (arr[index])
            for(index in sum downTo num) { //Start at sum, walk down to the number itself. This way, we only "take" the item once.
                arr[index] = arr[index] || arr[index - num]
            }
        }
        
        return arr[sum/2] //Return the half. If we can get exactly half in the array, then the other half should also be valid because 
    }
}
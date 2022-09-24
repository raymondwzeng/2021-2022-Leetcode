class Solution {
    fun threeSumClosest(nums: IntArray, target: Int): Int {
        /*
            Do 3 sum, but hold the closest, rather than the exact amount.
            
            Brute force: Try all of the combinations of 3 inputs (O(n^2) time)
            
            We might be able to do something in O(nlogn) after sorting
                -> For each (2) number, maybe do a binary search for the last? 
        */
        var closestNum = Integer.MAX_VALUE
        nums.sort()
        nums.forEachIndexed { index, currNum ->
            var next = index + 1
            var right = nums.size - 1
            while(next < right && next < nums.size) {
                if(currNum + nums[next] + nums[right] - target == 0) return target //Can't get closer than sum
                if(closestNum == Integer.MAX_VALUE || Math.abs(currNum + nums[next] + nums[right] - target) < Math.abs(closestNum - target)) closestNum = currNum + nums[next] + nums[right] //Update closest value if true
                if(currNum + nums[next] + nums[right] - target > 0) { // > 0, >target, move right down
                    right--
                } else { //If < 0, < target, move left up to increase sum total
                    next++
                }
            }
        }
        return closestNum
    }
}
class Solution {
    fun productExceptSelf(nums: IntArray): IntArray {
        /*
            Brute force: Calculate it manually every time -> O(n-1)*O(n) => O(n^2)
        
            L->R [0, 0*1, 0*1*2, ... , 0*...*n]
            L<-R [0 * ... * n, ... ,(n - 2) * (n - 1) * n, (n - 1) * n,n]
            
            productExceptSelf = L->R(0..i-1) * L<-R(i+1..n)
            
            Simple(r) solution: Run 2 passes through the input array to build up L->R and L<-R arrays, then use those cached values to build the output array.
            Time Complexity: O(n)
            Space Complexity: O(n)
            
            Slightly more complicated, optimized-ish version:
                -> We could probably get away on the L->R side with an accumulator, so we don't need that array
                -> We could probably reuse the output array, and slowly override in the values as we get there.
            Time Complexity: O(n)
            Space Complexity: O(1)
        */
        val outputArray = IntArray(nums.size)
        outputArray[nums.size - 1] = nums[nums.size - 1]
        for(i in nums.size - 2 downTo 0) { //Populate the output array with the values that we need
            outputArray[i] = outputArray[i + 1] * nums[i]
        }
        var accumulator = 1 //L->R
        for(i in 0..outputArray.size - 2) {
            outputArray[i] = accumulator * outputArray[i + 1]
            accumulator *= nums[i]
        }
        outputArray[outputArray.size - 1] = accumulator
        return outputArray
    }
}
/*
        //Sorting probably won't work
        //Sliding window
        //nums.size <= 2 * 10^4 -> O(n)-ish algorithm
        /*
            Corner case: If nums.size == 1, return nums[0]
            Start with a window of size 1, at the base value
            While the next value > 0, add it to the product and increase the window
            If the next value == 0, move the left side to index + 1, and shrink window to size 1
            If the next value < 0, then we have a bit of a problem
                -> This number could potentially product up to a larger value IFF we have another number which is < 0
                Do we want to grow the window as long as the value is not 0?
                Keep track of the negative numbers
                
            [2, 3, -2, 4, 2] vs [2, 3, -2, 4, -2] vs [-2, 3, 0, -4, 2]
            At each number < 0, we can choose to either extend the window, or reset the window
            Extending the window will only work to achieve the max if there exists another number < 0 within the subarray
            Resetting the window will only work if there is only 1 number < 0 within the subarray
            
            Two subarrays, one from the left to right, and another from right to left
            
            [2, 3, -2, 4, 2]
            [2, 3, -2] (left to right)
            [2, 4] (right to left, until we hit either a negative # or until the two windows meet?)
            
            [2, 3, -2, 4, -2]
            [2, 3, -2] (l2r)
            [-2] //Stops after multiplying a negative number
            [4] (l2r appended)
            If both are < 0, when they meet, then we can append them together
            
            [-2, 3, 0, -4, 2]
            [-2] (l2r)
            [2, -4] (r2l)
            [3] (l2r) //If # > currentMax && # * current < 0?
            Stops once we reset the window to -4 after jumping the 0
            
            [2, -3, -2, 4, -2]
            [2, -3]
            [-2]
            [-2, 4] //Because # < 0, we can take more negative numbers. Check if # < 0 before switching or not
        */
        if(nums.size == 1) return nums[0]
        var left = 0
        var right = 0
        var currentProduct = 1
        var prefixProduct = 1 //PrefixProduct is only for negative values
        var max = Integer.MIN_VALUE
        while(right < nums.size) {
            while(right < nums.size && nums[right] > 0) {
                currentProduct *= nums[right++]
                if(currentProduct > max) max = currentProduct
            }
            if(right < nums.size) {
                //At this point, nums[right] is either < 0 or == 0
                if(nums[right] == 0) {
                    if(max < 0) max = 0
                    left = right + 1
                    right++
                    currentProduct = 1
                    prefixProduct = 1
                } else {
                    if(prefixProduct < 0) {
                        currentProduct *= (nums[right] * prefixProduct)
                        if(currentProduct > max) max = currentProduct
                        prefixProduct = 1
                        right++
                    } else {
                        prefixProduct = currentProduct * nums[right]
                        left = right + 1
                        right++
                        currentProduct = 1
                    }
                }
            }
        }
        return max
    }
*/

class Solution {
    /*
        [2, 3*, -2, 4, 2] min = 2, max = 6
        [2, 3, -2*, 4, 2] min = num * max (-12), max = num (-2)
        [2, 3, -2, 4*, 2] min = num * min (-48), max = num (4)
        [2, 3, -2, 4, 2*] min = num * min (-96), max = num * max (8)
        
        [2, 3*, -2, 4, -2] min = 2, max = 6
        [2, 3, -2*, 4, -2] min = -12, max = 1?
        [2, 3, -2, 4*, -2] min = -48, max = 4
        [2, 3, -2, 4, -2*] min = 1?, max = num * min (96)
    */
    fun maxProduct(nums: IntArray): Int {
        if(nums.size == 1) return nums[0]
        var min = nums[0]
        var max = nums[0]
        var product = nums[0]
        nums.forEachIndexed { index, num ->
            if(index > 0) {
                val tempMax = max
                max = maxOf(num * min, num * max, num) //If we multiply a number < 0 with min, our result could be max
                //4 cases handled @ max: --, ++, +-/-+
                min = minOf(num * min, num * tempMax, num) //If we multiply a number < 0 with max, our result could be min 
                //4 cases handled @ min: +-, -+, ++/--
                if(max > product) product = max
            }
        }
        return product
    }
}
class Solution {
    fun sortedSquares(nums: IntArray): IntArray {
        //The easiest solution would be to square each element, then sort again -> O(n) + O(nlogn) = O(nlogn)
        //The problem happens because we have negative numbers -> Not great, because we might need to swap positions
        
        //Two pointer approach
        /*
            - Start each pointer at each end (0 and size - 1)
            - Check to see if the square of the left side will be larger than the square of the right side
                - If yes then swap, if not, leave then as-is and square
            - If we swapped, we want to move the right side down, and leave the left side.
            - If we did not swap, still don't move the left side, move right side down (we need to find the swap point)
            - Terminate when right <= 0   
                * Because the array is sorted, we won't have a situation where we would "miss" a swap
                - Swapped value will be in place, we don't need to care about it anymore because as we move up away from the negatives, we decrease in magnitude, and when we go above 0, the numbers squared will ALWAYS be less on the left
        */
        
        /*
            [-7*, -3, 2, 3, 11*] -> Compare -7 and 11, less, don't swap
            [-7*, -3, 2, 3*, 121] -> Compare -7 and 3, -7^2 > 3^2, swap
            [3*, -3, 2*, 49, 121] -> Compare 3 and 2, 3^2 > 2^2, swap
            [2*, -3*, 9, 49, 121] -> Compare 2 and -3, less, don't swap (square the right pointer, because it's done)
            Square the 0
            Return
            
            O(n) where n is the number of elements in the array
            
            Carveout: If RHS has a negative value AND we do not swap, then move LHS up.
                (Do we need to consider the value of LHS?)
                
            -> Maybe need to also square numbers between 0 and LHS?
            
            [-5*, -3, -2, -1*] -> Compare -5 and -1, -5^2 > -1^2, swap
            [-1*, -3, -2*, 25] -> Compare -1 and -2, don't swap, RHS < 0, move LHS up (square LHS)
            [1, -3*, -2*, 25] -> Compare -3 and -2, -3^2 > -2^2, swap
            [1, -2**, 9, 25] -> Square RHS
            
            [-3*, -2, -1, 1*] -> Compare -3 and 1, -3^2 > 1^2, swap
            [1*, -2, -1*, 9] -> Compare 1 and -1, same, don't swap, RHS < 0, move LHS up and square
            [1, -2*, -1*, 9] -> Compare -2 and -1, -2^2 > -1^2, swap
            [1, -1**, 4, 9] -> Square RHS
            
            *Rather than some weird RHS < 0 thing, what if
                -> On no swap, move LHS up
                -> On swap, move RHS down
                
            [-4*, -1, 0, 3, 10*]
            [-4, ]
            
            [-3*, -2, -1, 0, 1*] -> Swap, move RHS down
            [1*, -2, -1, 0*, 9] -> Swap, move RHS down again (not good) -> Why?
            [0*, -2, -1*, 1, 9]
            [0, -2*, -1*, 1, 9]
            [0, 1, 4, 1, 1, 9]
            
            -Some time later-
            
            A different approach
            -> If NOT swapping, check the cases:
                - (-) on the right AND NOT both (-) -> We're in the negatives, so reverse the "pull" direction from right->left to left->right. If both are (-) that means that we have swapped before, and now we move inwards to check.
                - (+) on the right -> Default case.
            -> If swapping, check the cases:
                - (-)(-) -> We need to move both pointers inwards, because in the sorted way that it is, we need to reorder the two.
                - (+-)(0) -> 0 is the minimum in magnitude. Thus, move left because we want to swap the rest, and do not move the right because we don't know if it needs to be swapped. (Essentially, "reset" by moving left up)
                - (-)(+)/(+)(-) -> Standard swap. Move right, because now the lesser magnitude needs to find its place.
                - (+)(+) -> Also standard swap, possible because of the negative number displacement.
                
            - A few hours later... -
            Split into 3 main cases: All positive (square), all negative (reverse then square), and mixture.
            
            We will tackle the mixture on our own.
            
            We essentially have a split, with the "zero point" (may not be 0) somewhere.
            Initially, Left to Right decreases in magnitude, Right to Left also (until the center).
            Our job is to essentially find that center, and then pivot elements around it.
            
            -> Each end of the array is its greatest in magnitude, working towards the center
                -> If an element does not move or is swapped into place (excl swap w/ 0), then it belongs there because it is the highest of the two highest elements.
                -> The zero exclusion
                    - The zero exclusion exists because zero is the absolute minimum magnitude, thus it must exist at the beginning of the array in the solution
                    - Thus even though we swap, we don't really know if a larger item exists - the left side is also a space for intermediate values that may have swapped in but are actually smaller in magnitude than the rest of the (-) side (think [-3, -3, -2, 0, 1] - we swap 1 with -3 but 1^2 < -3^2).
                    - Assuming it belongs is incorrect. We should move the left side up (now that it's a zero) and keep the RHS where it is to confirm or deny that it belongs there.
                    
                    (Let's put a lid on the in place method.)
                                    
        */
        var greatestNegativeIndex = 0
        var greatestPositiveIndex = nums.size - 1
        val numNew = IntArray(nums.size)
        if(nums[nums.size - 1] < 0) {
            return nums.reversed().map { num -> num * num }.toIntArray()
        }
        if(nums[0] >= 0) {
            return nums.map { num -> num * num }.toIntArray()
        }
        for(i in nums.size - 1 downTo 0) {
            //i is the index that we are replacing.
            val greatestNegative = if(nums[greatestNegativeIndex] < 0) nums[greatestNegativeIndex] else 0
            val greatestPositive = if(nums[greatestPositiveIndex] >= 0) nums[greatestPositiveIndex] else 0
            
            if (Math.pow(greatestNegative.toDouble(), 2.0) > Math.pow(greatestPositive.toDouble(), 2.0)) {
                numNew[i] = greatestNegative
                greatestNegativeIndex++
            } else {
                numNew[i] = greatestPositive
                greatestPositiveIndex--
            }
            numNew[i] *= numNew[i]
        }
        return numNew
    }
}
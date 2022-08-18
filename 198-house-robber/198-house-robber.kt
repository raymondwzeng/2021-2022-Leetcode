class Solution {
    fun rob(nums: IntArray): Int {
        /*
            Dynamming programming -> 0/1 Knapsack-like problem
            
            Suppose that we had n houses to consider
            
            At the n-th house, we need to keep two things in mind:
                -> We can rob the house and take the money if we did not rob the previous house
                -> We can skip the house and stay with our current amount.
                
            [1, 2, 3, 1]
             1  1  4  4
             0  2  2  3
             
              1, 0, 3, 4, 0
              1, 0, 3, 4, 0 //Initialization
              1, 1, 4, 5, 5
              1, 1, 4, 5, 5
              
              [1, 2, 3, 1]
              1, 2, 3, 1
              1, 2, 4, 4
             
             //We need two arrays...?
             Subproblem: Choose the max between (assuming T(n) is our function)
             maxOf(T(n - 2) + nums[n], T(n - 1))
             
             100 -> O(n^2) runtime
             O(n) space
        */
        if(nums.size == 1) return nums[0]
        val moneyEarned = IntArray(nums.size)
        
        nums.forEachIndexed { index, num ->
            for(i in index..nums.size - 1) {
                if(i - 2 >= 0) {
                    moneyEarned[i] = maxOf(moneyEarned[i - 2] + nums[i], moneyEarned[i - 1])
                } else if (i - 1 >= 0) {
                    moneyEarned[i] = maxOf(moneyEarned[i - 1], nums[i])
                } else {
                    moneyEarned[i] = nums[i]
                }
            }
        }
        
        return moneyEarned[nums.size - 1]
    }
}
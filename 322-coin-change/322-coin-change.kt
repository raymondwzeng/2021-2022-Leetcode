class Solution {
    fun coinChange(coins: IntArray, amount: Int): Int {
        /*
            DP problem -> Modeled in a similar way to the 0/1 knapsack problem
            
            Our subproblem for each coin c0..cn with an amount a
                -> f(a) = min_c0..cn(f(a - ci) + 1)
                -> 0 if a == 0
                - How do we determine if we can or can not make that change with our currency set?
                
            Each column represents a coin c0..cn
            Each row represents an amount 0..a
            
            For each cell, we will mark it as 0 if a == 0 || a < c (might be a -1)?
            If a > c, then maybe we should fill the cell as -1
            For each subsequent cell, we have 3 options
                -> Take the minimum from a previous column (we keep a minimum from c0...ci)
                -> Take the minimum from our current coin (f(a - ci) + 1, vertical)
                    Minimum that is in the previous cell could be using the same coin, or a min from another coin
            Time complexity: O(an) where n is the number of coin denominations that we have, a is the amount
            Space complexity: O(an) because we need a cell for each coin/amount combination
            
            
            1 2 5
        0   0 0 0
        1   1 1 1
        2   2 1 1
        3   3 2 2
        4
        5
        ...
        */
        val dp = Array(coins.size) { Array(amount + 1){0} }
        // for(column in 0..dp.size - 1) {
        //     print("${coins[column]} ")
        // }
        // println()
        for(row in 1..amount) {
            // print("Row:$row ")
            for(column in 0..dp.size - 1) {
                when {
                    // row == 0 -> dp[column][row] = 0
                    row == coins[column] -> dp[column][row] = 1
                    else -> {
                        val previousAmount = if(row - coins[column] < 0) -1 else dp[column][row - coins[column]]
                        val previousCoin = if(column - 1 < 0) -1 else dp[column - 1][row]
                        if(previousAmount != -1 && (previousAmount + 1 < previousCoin || previousCoin == -1)) {
                            dp[column][row] = previousAmount + 1
                        } else {
                            dp[column][row] = previousCoin
                        }
                    }
                }
                // print("${dp[column][row]} ")
            }
            // println()
        }
        
        return dp[coins.size - 1][amount]
    }
}
class Solution {
    fun climbStairs(n: Int): Int {
        //The quintesential DP problem.
        //If n = 1, there is only 1 way to climb.
        //If n = 2, there are 2 ways (climb 1 + 1 or 2)
        //If n = 3, there are 3 ways (1 + 2, 2 + 1, 1 + 1 + 1)
        //With a stair height of n, we know that there are dp[n-1] ways of reaching the n - 1 stair
        //As well as dp[n-2] ways of reaching n - 2.
        //From there, we know that there is 1 way of reaching n from each (either +1 or +2)
        //Thus dp[n] = dp[n-1] + dp[n-2]
        if (n == 1) return 1
        if (n == 2) return 2
        var beforePrevious = 1
        var previous = 2
        var now = 3
        for(i in 3..n-1) {
            beforePrevious = previous
            previous = now
            now = beforePrevious + previous
        }
        return now
        //The iterative approach requires a bit more boilerplate - recursively, we could just return climbStairs(n - 1) + climbStairs(n - 2)...but that would be no fun!
    }
}
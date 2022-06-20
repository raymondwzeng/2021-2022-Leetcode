class Solution {
    fun maxProfit(prices: IntArray): Int {
        //This is a dynamic programming kind of problem, as evidenced by the fact that we have overlapping subproblems (profit depends on when you bought in) and the optimal substructure (our best profits will exist across the entire problem.)
        //Start by declaring a current max array, set to 0, equal in size to our input.
        var max = 0 //Minor space + runtime optimization - we don't need the information from all days, only the most recent one.
        var localMin = prices[0] //Our lowest value so far, which we will use to check the profit.
        prices.forEach { value ->
            if(max < value - localMin) {
                max = value - localMin
            }
            if(value < localMin) localMin = value //Set the new low for future items
                //Optimal substructure here: If a new min exists, then any value after would achieve a higher profit by buying now and selling later vs. buying before and selling then.
            }
        return max
    }
}
class Solution {
    fun maxProfit(prices: IntArray): Int {
        //This is a dynamic programming-ish problem.
        /*
        Overlapping subproblems - we can either buy
        day 1, day 2, etc. or not buy at all.
        We can sell on day 2, day 3, etc.
        
        Optimal substructure - doesn't really apply in this case,
        since we only have 1 solution that doesn't "build"
        on any previous solution, but something to perhaps
        keep in mind for future BTBSS
        */
        var maxProfit = 0
        var firstIndex : Int? = prices.firstOrNull()
        firstIndex?.let { //Do only if array is not empty (that is, first index not null)
            var lowestSoFar : Int = firstIndex
            var profitArray = mutableListOf<Int>(maxProfit) //Track profits
            prices.forEachIndexed { index, element ->
                if(index > 0) { //We only want to start on the second instance
                    profitArray.add(Math.max(profitArray.get(index-1), element - lowestSoFar))
                    if(element < lowestSoFar) lowestSoFar = element
                }
            }    
            return profitArray.last()
        }
        return maxProfit
    }
}
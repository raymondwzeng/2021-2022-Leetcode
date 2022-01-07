class Solution {
    public int maxProfit(int[] prices) {
        //Dynamic programming solution inspired by House Robber.
        //Accumulate the max at our current point, and then see if anything can beat it.
        //Assume that we hold onto our lowest for possible selling at any time.
        //O(n) runtime due to only 1 iteration through the list, O(n) space due to holding all of the max.
        //We can further reduce space by only holding onto the most recent (and last) profits.
        // int[] max = new int[prices.length]; //Further optimized.
        int previousMax = 0;
        int lowest = prices[0];
        for(int i = 1; i < prices.length; i++) {
            previousMax = Math.max(prices[i] - lowest, previousMax);
            if(prices[i] < lowest) lowest = prices[i];
        }
        return previousMax;
    }
}
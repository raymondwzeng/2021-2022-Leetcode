class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length <= 1) return 0; //A bit unnecessary as prices.length has a lower bound as 1 but /shrug
        int profit = 0;
        int buyIndex = 0;
        int sellIndex = 0;
        for(int i = 0; i < prices.length; i++)
        {
            if(prices[i] < prices[buyIndex]) {
                //If a better min is found, then we move the buy to it.
                //We don't care for jumping, as we save our most recent profit.
                buyIndex = i;
                if(i > sellIndex) { //If, however, it is also greater than sellIndex, move that up too.
                    //Because now we need a new sellIndex.
                    sellIndex = i;
                }
            }
            if(prices[i] - prices[buyIndex] > profit) { //If the price is higher than the sell...
                //Check if the index is after
                if(i > sellIndex && i > buyIndex) {
                    profit = prices[i] - prices[buyIndex];
                    sellIndex = i;
                }
            }
        }
        return profit;
    }
}
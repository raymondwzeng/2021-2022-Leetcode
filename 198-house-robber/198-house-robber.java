class Solution {
    public int rob(int[] nums) {
        //Dynamic programming problem, solution courtesy of NeetCode on YouTube
        //Think about it like this - if we take down the first house, how do we determine whether or not to rob the next?
        //Well, we can think about it as a subproblem of size [i+2, n].
        //Recurrence relation problems lend well to dynamic programming optimization. So we can *check* to see if there is a new max at that point
        //from 2 houses ago.
        int[] maxAmount = new int[nums.length];
        maxAmount[0] = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if(i < 2) {
                maxAmount[i] = Math.max(nums[i], maxAmount[i-1]);
            } else {
                maxAmount[i] = Math.max(nums[i] + maxAmount[i-2], maxAmount[i-1]); //We can either take the previous, or take even more previous + this  
            }
        }
        
        //That fills up our maxAmount list. Then we check the second to last, or the last, and return the greater of the two.
        //Actually, since we "snowball up" the max amount, we don't need to hit math max!
        // return Math.max(maxAmount[nums.length - 1], maxAmount[nums.length - 2]);
        return maxAmount[nums.length - 1];
    }
}
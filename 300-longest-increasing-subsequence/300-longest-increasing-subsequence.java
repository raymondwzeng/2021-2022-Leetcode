class Solution {
    //Dynamic programming problem from CS141.
    //At each number, we have the possibility of adding on to a chain from before, increasing the length by 1, so long as the value is greater than the previous.
    //Keep track of this number, and we're home free.
    //Time complexity: O(n^2) [We have to recheck 1, then 2..n = n(n+1)/2], Space complexity: O(n) [Keep track of all chain lengths. Perhaps a better way at some point?]
    public int lengthOfLIS(int[] nums) {
        int[] LIS = new int[nums.length];
        int longestIS = 1; //Default to 1
        Arrays.fill(LIS, 0, LIS.length, 1); //Fill all default values with 1, because by default each number is its own subsequence.
        for(int i = 1; i < nums.length; i++) {
            for(int j = 0; j < i; j++) { //Go through all previous values.
                if(nums[j] < nums[i] && LIS[i] < LIS[j] + 1) { //If the previous value is less in nums, and the chain is longer...
                    LIS[i] = LIS[j] + 1; //Update the value.
                    if(LIS[i] >= longestIS) longestIS = LIS[i];
                }
            }
        }
        return longestIS;
    }
}
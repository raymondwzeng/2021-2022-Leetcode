//Solution from @mingwe on LC. 
class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0]; //Insight #1: We don't need the actual array! So just run a total.
        if(nums.length == 1) return nums[0];
        for(int i = 1 ; i < nums.length; i++) {
            if(nums[i-1] > 0) { //If our previous value is positive, add to current value.
                nums[i] = (nums[i] + nums[i-1]); //This way, we get a "running total" that follows i.
            } //We know that such a running total exists, and is the max (we set next lines).
            if(maxSum < nums[i]) { //If we find a value which is even greater than our running total, swap.
                maxSum = nums[i];
            } //We basically set the max and add it to the total so far.
            //Q: Is this step optional? Couldn't we just run the total?
        }
        return maxSum;
    }
}
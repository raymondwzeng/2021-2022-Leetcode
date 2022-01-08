class Solution {
    public boolean canJump(int[] nums) {
        //The naive solution in this case is to check all possible combinations from 0..n-2, breaking when you have at least 1 solution.
        //However, this would require n steps in step 1, then n-1, then n-2...so n(n-1)/2 or O(n^2) runtime.
        //Can we do better? This kinda *feels* like a, uh, dynamic programming problem, but I've been studying them for the past couple of days...
        //Initial solution is O(n^2) due to the max possible work if we have a strictly decreasing to 0 cell (as in ex. 2) resulting in a close summation
        //Space in this case is also O(n). Ouch.
        //Edge case: 1 value.
        if(nums.length == 1) return true; //We can always go from a single cell to itself.
        boolean[] solutions = new boolean[nums.length];
        for(int i = nums.length - 2; i >= 0; i--) {
            if(nums[i] >= nums.length-1-i) {
                solutions[i] = true;
            } else {
                for(int j = nums[i]; j > 0; j--) {
                    if(solutions[i+j] == true) {
                        solutions[i] = true;
                        break; //Micro-optimization, might not actually do anything, but either way we only need 1 solution.
                    }
                } //solutions[i] will remain 0 if nothing happens.
            }
        }
        return solutions[0];
    }
}
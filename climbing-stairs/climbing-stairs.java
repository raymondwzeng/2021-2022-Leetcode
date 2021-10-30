class Solution {
    public int climbStairs(int n) {
        if(n == 0 || n == 1) return 1;
        int[] steps = new int[n];
        steps[0] = 1;
        steps[1] = 1;
        for(int i = 2; i < n; i++) {
            steps[i] = steps[i-1] + steps[i-2];
        }
        return steps[n-1] + steps[n-2]; //O(n^2) solution without DP, O(n) with.
    }
}
class Solution {
    public void sort(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for(int j = i; j < nums.length; j++) {
                if(nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            //Swap min index with i
            int temp = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = temp;
        }
        return;
    }
    
    
    public int missingNumber(int[] nums) {
        //We could sort and count increasing, that would be O(nlog(n)) + O(n) => O(nlog(n))
        //Is there an O(n) solution with a data structure? 
        if(nums.length == 0) return 0;
        sort(nums);
        for(int i = 0; i < nums.length; i++) { //Go to the 2nd to last, so we can compare 1 more.
            if(nums[i] != i) {
                return i; //If we notice a "gap" between current and next value, return the gap.
            }
        }
        return nums.length;
    }
}
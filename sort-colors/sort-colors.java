class Solution {
    public void sortColors(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for(int j = i; j < nums.length; j++) //Just selection sort for now.
                {
                    if (nums[j] < nums[minIndex]) {
                        minIndex = j;
                    }
                }
            int temp = nums[i]; //Swap values at the end
            nums[i] = nums[minIndex];
            nums[minIndex] = temp;
        }
        
    }
}
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        //First, this problem can be accomplished much easier with a sort.
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        //This way, as we traverse through the array, we won't have an issue with "missing" solutions with a greater negative.
        for(int i = 0; i < nums.length - 2; i++) { //Go to 3rd to last value at most, because beyond that we don't have enough numbers to make it work.
            if(i == 0 || (i > 0 && nums[i - 1] != nums[i])) {
                int left = i + 1;
                int right = nums.length - 1;
                int requiredSum = -nums[i];
                while(left < right) {
                    int sum = nums[left] + nums[right];
                    if(sum == requiredSum) {
                        result.add(Arrays.asList(nums[left], nums[right], nums[i]));
                        while(left < right && nums[left] == nums[left+1]) left++;
                        while(left < right && nums[right] == nums[right-1]) right--;
                        left++;
                        right--;
                    } else if(sum < requiredSum) {
                        //If the value we got is less than what we need, increase the bottom limit
                        left++;
                    } else {
                        right--; //Opposite for too large.
                    }
                }
            }
        }
        return result;
    }
}
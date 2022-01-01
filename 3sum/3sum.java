class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new LinkedList<>();
        for(int i = 0; i < nums.length-2; i++) { //The reason why we do this is because we can't have 3 numbers ijk if the length has less than 3 values to choose from
            if(i == 0 || (i > 0 && nums[i] != nums[i-1])) {
                int left = i+1;
                int right = nums.length - 1;
                int numWeWant = -nums[i];
            while(left < right) {
                int sum = nums[left] + nums[right];
                if(sum == numWeWant) {
                    result.add(Arrays.asList(nums[left], nums[right], nums[i]));
                    while(left < right && nums[left + 1] == nums[left]) {
                        left++;
                    }
                    while(left < right && nums[right - 1] == nums[right]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if(sum < numWeWant) {
                    left++;
                } else {
                    right--;
                }
            }
            }
        }
        return result;
    }
}
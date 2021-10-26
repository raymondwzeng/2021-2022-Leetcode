class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> numIndexMap = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++) 
        {
            if(numIndexMap.containsKey(target-nums[i]))
            {
                return new int[] {numIndexMap.get(target-nums[i]), i};
            } else {
                numIndexMap.put(nums[i], i);
            }
        }
        return new int[] {-1, -1};
    }
}